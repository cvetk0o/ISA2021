import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page/welcome-page.component';
import { SignupComponent } from './components/sign-up/signup/signup.component';
import { SignInComponent } from './components/sign-in/sign-in/sign-in.component';
import { AuthGuard } from './helpers/auth.guard';
import { PatientHomePageComponent } from './components/patient-home-page/patient-home-page/patient-home-page.component';
import { PharmacyAdminHomePageComponent } from './components/pharmacy-admin-home-page/pharmacy-admin-home-page/pharmacy-admin-home-page.component';


const routes: Routes = [
  { path: '' , component: WelcomePageComponent } ,
  { path: 'welcomePage' , component: WelcomePageComponent  },
  { path: 'patientHomePage' , component: PatientHomePageComponent , canActivate: [AuthGuard] , data: { roles: [ 'PATIENT']} },
  { path: 'pharmacyAdminHomePage' , component: PharmacyAdminHomePageComponent, canActivate: [AuthGuard] , data: { roles: [ 'PHARMACY_ADMIN']} },
  { path: 'signUp' , component: SignupComponent },
  { path : 'signIn' , component: SignInComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }