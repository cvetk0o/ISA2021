import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { WelcomePageComponent } from './components/welcome-page/welcome-page/welcome-page.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar/navigation-bar.component';
import { SignupComponent } from './components/sign-up/signup/signup.component';
import { ControlMessageComponent } from './validation/control-message/control-message.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { SignInComponent } from './components/sign-in/sign-in/sign-in.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorInterceptor } from './helpers/error.interceptor';
import { JwtInterceptor } from './helpers/jwt.interceptor';
import { PatientHomePageComponent } from './components/patient-home-page/patient-home-page/patient-home-page.component';
import { PharmacyAdminHomePageComponent } from './components/pharmacy-admin-home-page/pharmacy-admin-home-page/pharmacy-admin-home-page.component';
import { SearchPharmacyComponent } from './components/search-pharmacy/search-pharmacy/search-pharmacy.component';
import { ActivateUserComponent } from './components/activate-user/activate-user/activate-user.component';
import { UpdateUserInfoComponent } from './components/update-user-info/update-user-info/update-user-info.component';
import { DrugReservationComponent } from './components/drug-reservation/drug-reservation/drug-reservation.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list/reservation-list.component';
import { ConsultingReservationComponent } from './components/consulting-reservation/consulting-reservation/consulting-reservation.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list/pharmacy-list.component';
import { PharmacyProfileComponent } from './components/pharmacy-profile/pharmacy-profile/pharmacy-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    NavigationBarComponent,
    SignupComponent,
    ControlMessageComponent,
    SignInComponent,
    PatientHomePageComponent,
    PharmacyAdminHomePageComponent,
    SearchPharmacyComponent,
    ActivateUserComponent,
    UpdateUserInfoComponent,
    DrugReservationComponent,
    ReservationListComponent,
    ConsultingReservationComponent,
    PharmacyListComponent,
    PharmacyProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: 
  [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
