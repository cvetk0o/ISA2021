import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Error } from 'src/app/model/Error';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

    signUpForm = this.fb.group({
    name: ['', Validators.required],
    lastname: ['', Validators.required],
    email: ['', [Validators.required]],
    password1: ['', Validators.required],
    password2: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    city: ['', Validators.required],
    street: ['', Validators.required],
    country: ['', Validators.required],
    number: ['', Validators.required  ]
  });

  constructor(private fb: FormBuilder, private authService: AuthService ,private router: Router) { }

  ngOnInit() {
  }


  signUp() {


    
    if (this.signUpForm.controls['password1'].value === this.signUpForm.controls['password2'].value) {
      console.log('isti su');

        this.authService.signUp(this.signUpForm.value).subscribe( (data:ApiResponse) => {
          alert(data.message)
          this.router.navigate(["/welcomePage"]);
          
        } ,
        (error: Error)=>{
          alert(error.errors);
        });

    } else {
      alert("passwords don't match")
    }

  }

}
