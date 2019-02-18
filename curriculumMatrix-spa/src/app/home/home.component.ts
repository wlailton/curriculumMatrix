import { Component, OnInit } from '@angular/core';

import { UserService } from '../core/user/user.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  private hasRoleAdmin: boolean = false;
  private hasRoleCoordinator: boolean = false;
  private hasRoleProfessor: boolean = false;
  private hasRoleStudent: boolean = false;
  private roles = [];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.roles = this.userService.getUserRoles();
    this.hasRoleAdmin = this.hasRole('ROLE_ADMIN');
    this.hasRoleCoordinator = this.hasRole('ROLE_COORDINATOR');
    this.hasRoleProfessor = this.hasRole('ROLE_PROFESSOR');
    this.hasRoleStudent = this.hasRole('ROLE_STUDENT');
    
  }

  hasRole(roleTest:string) {
    let test: boolean = false;
    this.roles.forEach(role => {
        if(role.authority === roleTest)
          test = true
      });
    return test;
  }

}


