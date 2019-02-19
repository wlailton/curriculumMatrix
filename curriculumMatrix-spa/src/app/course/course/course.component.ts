import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { CourseService } from '../course.service';
import { Course } from '../course';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  id: string = '';
  course: Course;
  courseForm: FormGroup;

  constructor(
    private activateRoute: ActivatedRoute,
    private courseService: CourseService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.courseForm = this.formBuilder.group({
      name: [' ', [Validators.required]]
    });

    this.id = this.activateRoute.snapshot.params.id;
    console.log(this.id);

    this.courseService
    .getCourse(this.id)
    .subscribe(course => {
      this.course = course;
      console.log(course);
    });
  }

  save() {
    const name = this.courseForm.get('name').value;
    
    this.courseService
    .updateCourse(this.id, name)
    .subscribe(
        (course) => console.log(course),
        err => {
            console.log(err);
            alert('Invalid user name os password');
        }
    );
  }

}
