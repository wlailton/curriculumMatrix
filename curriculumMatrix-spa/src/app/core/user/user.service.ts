import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as jwt_decode from 'jwt-decode';

import { TokenService } from '../token/token.service';
import { User } from './user';

@Injectable({ providedIn: 'root' })
export class UserService {

    private userSubjet = new BehaviorSubject<User>(null);
    private name: string;
    private userRoles: any[];

    hasRoleAdmin: boolean = false;
    hasRoleCoordinator: boolean = false;
    hasRoleProfessor: boolean = false;
    hasRoleStudent: boolean = false;

    constructor(
        private tokenService: TokenService
        ) {
        this.tokenService.hasToken() &&
            this.decodeAndNotify();
    }

    setToken(token: string) {
        this.tokenService.setToken(token);
        this.decodeAndNotify();
    }

    getUser() {
        return this.userSubjet.asObservable();
    }

    logout() {
        this.tokenService.removeToken();
        this.userSubjet.next(null);
    }

    isLogged() {
        return this.tokenService.hasToken();
    }
    getName() {
        return this.name;
    }
    getUserRoles() {
        return this.userRoles;
    }

    private decodeAndNotify() {
        const token = this.tokenService.getToken();
        const user = jwt_decode(token) as User;
        this.name = user.name;
        this.userRoles = user.roles;
        this.loadAccess();
        this.userSubjet.next(user);
    }
    
    loadAccess() {
        this.hasRoleAdmin = this.hasRole('ROLE_ADMIN');
        this.hasRoleCoordinator = this.hasRole('ROLE_COORDINATOR');
        this.hasRoleProfessor = this.hasRole('ROLE_PROFESSOR');
        this.hasRoleStudent = this.hasRole('ROLE_STUDENT');
    }

    hasRole(roleTest:string) {
        let test: boolean = false;
        this.userRoles.forEach(role => {
            if(role.authority === roleTest)
              test = true
          });
        return test;
      }

}