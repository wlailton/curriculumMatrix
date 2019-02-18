import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as jwt_decode from 'jwt-decode';

import { TokenService } from '../token/token.service';
import { User } from './user';

@Injectable({ providedIn: 'root' })
export class UserService {

    private userSubjet = new BehaviorSubject<User>(null);
    private login: string;

    constructor(private tokenService: TokenService) {
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

    getUserName() {
        return this.login;
    }

    private decodeAndNotify() {
        const token = this.tokenService.getToken();
        const user = jwt_decode(token) as User;
        this.login = user.login;
        this.userSubjet.next(user);
    }
}