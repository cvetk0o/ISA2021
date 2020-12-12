import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page/welcome-page.component';
import { SignupComponent } from './components/sign-up/signup/signup.component';
import { SignInComponent } from './components/sign-in/sign-in/sign-in.component';
import { AuthGuard } from './helpers/auth.guard';


const routes: Routes = [
  { path: '' , redirectTo: '/welcomePage' , pathMatch: 'full' } ,
  { path: 'welcomePage' , component: WelcomePageComponent , canActivate: [AuthGuard] , data: { roles: [ 'PATIENT'] } },
  { path: 'signUp' , component: SignupComponent},
  { path : 'signIn' , component: SignInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }