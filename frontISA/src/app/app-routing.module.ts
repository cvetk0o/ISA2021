import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page/welcome-page.component';
import { SignupComponent } from './components/sign-up/signup/signup.component';
import { SignInComponent } from './components/sign-in/sign-in/sign-in.component';
import { AuthGuard } from './helpers/auth.guard';
import { PatientHomePageComponent } from './components/patient-home-page/patient-home-page/patient-home-page.component';
import { PharmacyAdminHomePageComponent } from './components/pharmacy-admin-home-page/pharmacy-admin-home-page/pharmacy-admin-home-page.component';
import { ActivateUserComponent } from './components/activate-user/activate-user/activate-user.component';
import { DrugReservationComponent } from './components/drug-reservation/drug-reservation/drug-reservation.component';
import { ConsultingReservationComponent } from './components/consulting-reservation/consulting-reservation/consulting-reservation.component';


const routes: Routes = [
  { path: '' , component: WelcomePageComponent } ,
  { path: 'welcomePage' , component: WelcomePageComponent  },
  { path: 'patientHomePage' , component: PatientHomePageComponent , canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']} },
  { path: 'pharmacyAdminHomePage' , component: PharmacyAdminHomePageComponent, canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PHARMACY_ADMIN']} },
  { path: 'signUp' , component: SignupComponent },
  { path : 'signIn' , component: SignInComponent},
  { path: 'activationPage/:id' , component: ActivateUserComponent},
  { path: 'drugReservation' , component: DrugReservationComponent},
  { path: 'consultingReservation' , component: ConsultingReservationComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }