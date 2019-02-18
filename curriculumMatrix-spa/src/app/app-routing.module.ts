import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { CourseComponent } from './course/course/course.component';
import { SignupComponent } from './auth/signup/signup.component';
import { SigninComponent } from './auth/signin/signin.component';

const routes: Routes = [
    { 
      path: '', 
      component: IndexComponent
    },
    { 
      path: 'signup',
      component: SignupComponent
    },
    { 
      path: 'signin',
      component: SigninComponent
    },
    { 
      path: 'course/:id', 
      component: CourseComponent
    }
  ];
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}