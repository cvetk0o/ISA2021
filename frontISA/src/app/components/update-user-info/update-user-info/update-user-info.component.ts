import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Error } from 'src/app/model/Error';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-update-user-info',
  templateUrl: './update-user-info.component.html',
  styleUrls: ['./update-user-info.component.css']
})
export class UpdateUserInfoComponent implements OnInit {

  penalties;
  
  updateForm = this.fb.group({
    name: ['', Validators.required],
    lastname: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    city: ['', Validators.required],
    street: ['', Validators.required],
    country: ['', Validators.required],
    number: ['' , Validators.required]
  });

  passwordForm = this.fb.group({
    password1: ['',Validators.required],
    password2: ['', Validators.required]
  })
  constructor( private fb: FormBuilder , private userService: UserService ,private router: Router) { }

  ngOnInit() {
    this.userService.getMyInfo().subscribe( (data:User) => {
      console.log(data);
      this.penalties = data.penal;
      this.updateForm.patchValue( {
        name : data.name,
        lastname : data.lastname,
        city: data.address.city,
        country: data.address.country,
        number : data.address.number,
        phoneNumber : data.phoneNumber,
        street : data.address.street

      })
    })
  }



  update() {

    console.log(this.updateForm);
    
      this.userService.updateMyInfo(this.updateForm.value). subscribe( (data:ApiResponse) => {
        alert(data.message);
        window.location.reload();
      })
  }

  updatePassword() {

    if (this.passwordForm.controls['password1'].value === this.passwordForm.controls['password2'].value) {
      console.log('isti su');

        this.userService.updatePassword(this.passwordForm.value).subscribe( (data:ApiResponse) => {
          alert("uspesno :"+data.message)
          //this.router.navigate(["/welcomePage"]);
          
        } ,
        (error: Error)=>{
          console.log(error);
          alert("greska : "+error.errors);
        });

    } else {
      alert("passwords don't match")
    }

  }

}
