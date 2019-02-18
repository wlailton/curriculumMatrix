import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { UserService } from '../core/user/user.service';

@Injectable({providedIn: 'root'})
export class IsLoggedGuard implements CanActivate {
    
    constructor(private userService: UserService, private router: Router) {}

    canActivate(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        if(this.userService.isLogged()) {
            return true;
        } else {
            this.router.navigate(['']);
        }
        return false;
    }
}