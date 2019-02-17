import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserSignup } from './userSignup.inteface'
import { AuthService } from '../auth.service'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  roles:  Array<string> = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    this.signupForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      username: ['', Validators.required],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  roleSelected(e) {
    if(e.target.checked){  
       this.roles.push(e.target.value);   
    } else {
      var index = this.roles.indexOf(e.target.value);
      if (index > -1) {
        this.roles.splice(index, 1);
      }
    }
 }

  signup() {
    
    const user = this.signupForm.getRawValue() as UserSignup;
    user.role = this.roles;
    

    this.authService
    .signup(user)
    .subscribe(
        () => alert('Usuário criado com sucesso'),
        err => {
          alert('Error signing up!');
        }
    );
  }

}
