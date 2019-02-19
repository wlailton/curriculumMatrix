import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { RouterModule } from '@angular/router';
import { CourseComponent } from './course/course.component';
import { MatrixComponent } from './matrix/matrix.component';
import { CoursesComponent } from './courses/courses.component';

@NgModule({
    declarations: [
        CourseComponent,
        MatrixComponent,
        CoursesComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule
    ]
})
export class CourseModule {

}