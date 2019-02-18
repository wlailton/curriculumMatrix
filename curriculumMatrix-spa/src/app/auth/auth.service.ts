import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { UserSignup } from './signup/userSignup.inteface';
import { UserSignin } from './signin/userSignin.inteface';
import { UserService } from '../core/user/user.service';

@Injectable({
    providedIn: 'root'
  })
  export class AuthService {
    
    private baseUrl = environment.baseUrl;
  
    constructor(
      private http: HttpClient,
      private userService: UserService
    ) {}
  
    signup(user: UserSignup) {
      return this.http
      .post(this.baseUrl + '/api/auth/signup', user, { observe: 'response' })
      .pipe(tap(
        res => console.log(res),
        error => console.log("POST call in error: ", error)
      ));     
    }

    signin(user: UserSignin) {
      return this.http
      .post(this.baseUrl + '/api/auth/signin', user, { observe: 'response' })
      .pipe(tap(
        res => {
          const authToken = res.body['tokenType'] + ' ' + res.body['accessToken']
          this.userService.setToken(authToken);
        },
        error => console.log("POST call in error: ", error)
      ));     
    }
  
  }