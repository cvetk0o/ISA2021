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


@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    NavigationBarComponent,
    SignupComponent,
    ControlMessageComponent,
    SignInComponent,
    PatientHomePageComponent,
    PharmacyAdminHomePageComponent
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
