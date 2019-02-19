import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../core/user/user.service';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  private roles = [];
  private year: string;
  private courses: any[];

  constructor(
    private userService: UserService,
    private router: Router,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.roles = this.userService.getUserRoles();
    this.year = new Date().getFullYear().toString();

    if(!this.hasRoleProfessoOrStudent()) {
      this.router.navigate(['']);
      return;
    }
    
    this.getCourses();
  }

  hasRoleProfessoOrStudent () {
    let test: boolean = false;
    this.roles.forEach(role => {
        if(role.authority === 'ROLE_PROFESSOR' || role.authority === 'ROLE_STUDENT')
          test = true
      });
    return test;
  }
  
  getCourses() {
    this.courseService
      .getCoursesHasMatrix(this.year)
      .subscribe(courses => {
        this.courses = courses;
      });
    }

}
