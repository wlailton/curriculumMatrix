import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from '../index/index.component';
import { CourseComponent } from '../course/course/course.component';
import { SignupComponent } from '../auth/signup/signup.component';
import { SigninComponent } from '../auth/signin/signin.component';
import { HomeComponent } from '../home/home.component';
import { AuthGuard } from './auth.guard';
import { IsLoggedGuard } from './is-logged.guard';

const routes: Routes = [
    { 
      path: '', 
      component: IndexComponent,
      canActivate: [AuthGuard]
    },
    { 
      path: 'signup',
      component: SignupComponent
    },
    { 
      path: 'signin',
      component: SigninComponent,
      canActivate: [AuthGuard]
    },
    { 
      path: 'home',
      component: HomeComponent,
      canActivate: [IsLoggedGuard]
    },
    { 
      path: 'course/:id', 
      component: CourseComponent,
      canActivate: [IsLoggedGuard]
    }
  ];
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}