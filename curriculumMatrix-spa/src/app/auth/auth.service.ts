import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { UserSignup } from './signup/userSignup.inteface'
@Injectable({
    providedIn: 'root'
  })
  export class AuthService {
    
    private baseUrl = environment.baseUrl;
  
    constructor(
      private http: HttpClient
    ) {}
  
    signup(user: UserSignup) {
      console.log(this.baseUrl);
      return this.http
      .post(this.baseUrl + '/api/auth/signup', user, { observe: 'response' })
      .pipe(tap(
        res => {
            console.log(res)
        },
        error => {
            console.log("POST call in error: ", error);
        }
      ));     
    }
  
  }