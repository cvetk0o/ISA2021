import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  returnUrl: string;
  error = '';

  signInForm = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthService) {

    if (this.authenticationService.currentUserValue) { 
      this.router.navigate(['/']);
     
  }
   }

  ngOnInit() {

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  signIn() {
    
    this.authenticationService.signIn( this.signInForm.value.email , this.signInForm.value.password)
    .pipe( first())
    .subscribe( 
      data => {
      this.router.navigate([this.returnUrl]);
  },
  error => {
      this.error = error;
      
  }

    )
  }



}
