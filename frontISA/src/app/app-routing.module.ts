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
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list/pharmacy-list.component';
import { PharmacyProfileComponent } from './components/pharmacy-profile/pharmacy-profile/pharmacy-profile.component';
import { RatingPageComponent } from './components/rating-page/rating-page/rating-page.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list/reservation-list.component';
import { FinishedStuffComponent } from './components/finished-stuff/finished-stuff/finished-stuff.component';


const routes: Routes = [
  { path: '' , component: WelcomePageComponent } ,
  { path: 'welcomePage' , component: WelcomePageComponent  },
  { path: 'patientHomePage' , component: PatientHomePageComponent , canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']} },
  { path: 'pharmacyAdminHomePage' , component: PharmacyAdminHomePageComponent, canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PHARMACY_ADMIN']} },
  { path: 'signUp' , component: SignupComponent },
  { path : 'signIn' , component: SignInComponent},
  { path: 'reservationList' , component: ReservationListComponent , canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']}},
  { path: 'finishedStuff' , component: FinishedStuffComponent , canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']}},
  { path: 'activationPage/:id' , component: ActivateUserComponent},
  { path: 'drugReservation' , component: DrugReservationComponent, canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']}},
  { path: 'consultingReservation' , component: ConsultingReservationComponent ,canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']}},
  { path: 'pharmacyList' , component: PharmacyListComponent},
  { path: 'pharmacyProfile/:id' ,component: PharmacyProfileComponent},
  { path: 'ratingPage' ,component: RatingPageComponent  , canActivate: [AuthGuard] , data: { roles: [ 'ROLE_PATIENT']}},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }