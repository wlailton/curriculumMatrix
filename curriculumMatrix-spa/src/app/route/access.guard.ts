import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { UserService } from '../core/user/user.service';

@Injectable({providedIn: 'root'})
export class AccessGuard implements CanActivate {
    
    constructor(
        private userService: UserService, 
        private router: Router
    ) {}

    canActivate(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        
        if(state.url === '/courses' && (this.userService.hasRoleProfessor || this.userService.hasRoleStudent)) {
            return true;
        } if(state.url.search('/course/matrix/') != -1 && (this.userService.hasRoleProfessor || this.userService.hasRoleStudent)) {
            return true;
        } if(state.url === '/course/new' && this.userService.hasRoleCoordinator) {
            return true;
        } else {
            alert('User without access!')
            this.router.navigate(['']);
        }
        
        return false;
    }
}