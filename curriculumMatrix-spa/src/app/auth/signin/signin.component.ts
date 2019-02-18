import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserSignin } from './userSignin.inteface'
import { AuthService } from '../auth.service'

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  signinForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    this.signinForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required]]
    });
  }

  signin() {
    const user = this.signinForm.getRawValue() as UserSignin;
    this.authService
      .signin(user)
      .subscribe(
        () => {
          this.router.navigate([''])
        },
        err => {
          alert('Error signing in!');
        }
    );
  }

}
