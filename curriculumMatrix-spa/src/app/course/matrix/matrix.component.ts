import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../../core/user/user.service';
import { CourseService } from '../course.service';
import { MatrixItem } from './matrix-item.interface';

@Component({
  selector: 'app-matrix',
  templateUrl: './matrix.component.html',
  styleUrls: ['./matrix.component.css']
})
export class MatrixComponent implements OnInit {
  private roles = [];
  private courseId: string = '';
  private year: string = '';
  private matrix: MatrixItem[];

  constructor(
    private userService: UserService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.roles = this.userService.getUserRoles();
    if(!this.hasRoleProfessoOrStudent()) {
      this.router.navigate(['']);
      return;
    }

    this.year = new Date().getFullYear().toString();
    this.courseId = this.activateRoute.snapshot.params.courseId;
    this.getMatrix();
  }

  hasRoleProfessoOrStudent () {
    let test: boolean = false;
    this.roles.forEach(role => {
        if(role.authority === 'ROLE_PROFESSOR' || role.authority === 'ROLE_STUDENT')
          test = true
      });
    return test;
  }

  getMatrix() {
    this.courseService
      .getCourseMatrix(this.year, this.courseId)
      .subscribe(matrix => {
        this.matrix = matrix;
      });
    }

    getCourseName() {
      return  this.matrix ?  this.matrix[0][0] : '';
    }

}
