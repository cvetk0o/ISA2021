import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Error } from 'src/app/model/Error';
import { Pharmacy } from 'src/app/model/Pharmacy';
import { User } from 'src/app/model/User';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-rating-page',
  templateUrl: './rating-page.component.html',
  styleUrls: ['./rating-page.component.css']
})
export class RatingPageComponent implements OnInit {
  ratePharmacistDiv = false;
  rateDermatologistDiv = false;
  ratePharmacyDiv = false;

  ratingForm = this.fb.group({
    id : ['',Validators.required],
    rating: ['' ,Validators.required]
  });

  pharmacists: User[];
  dermatologists: User[];
  pharmacies: Pharmacy[];

  constructor(private fb: FormBuilder,private pharmacyService: PharmacyService , private userService: UserService) { }

  ngOnInit() {
 
  }


  ratePharmacists(){
    this.userService.getMyPharmacists().subscribe((data:User[])=>{
      console.log(data);
      this.pharmacists =data;

    }, (error:Error) =>{
      alert(error.errors)
    })
   
    this.ratePharmacistDiv=true;
    this.rateDermatologistDiv = false;
    this.ratePharmacyDiv=false;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })

  }


  ratePharmacistsSubmit() {
    console.log(this.ratingForm.value);
    
    this.userService.ratePharmacist(this.ratingForm.value).subscribe(data => {
      console.log(data);
      
    }, (error:Error) => {

      if(error.errors[0] === "You have already rated this pharmacist") {
        if(confirm("You have already rated this pharmacist! Do you want to submit new rate?")) {
          console.log("Implement delete functionality here");
          this.userService.overrideRatePharmacist(this.ratingForm.value).subscribe((data:ApiResponse)=>{
            alert(data.message);
          })
      } else{

      console.log("implement nooooo");
      }
    }
    })
  }


  rateDermatologists() {
    this.userService.getMyDermatologists().subscribe((data:User[])=>{
      console.log(data);
      this.dermatologists =data;

    }, (error:Error) =>{
      alert(error.errors)
    })
   

    this.ratePharmacistDiv=false;
    this.rateDermatologistDiv = true;
    this.ratePharmacyDiv=false;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })
  }


  rateDermatologistSubmit(){
  
    this.userService.rateDermatologist(this.ratingForm.value).subscribe(data => {
      console.log(data);
      
    }, (error:Error) => {

      if(error.errors[0] === "You have already rated this dermatologist") {
        if(confirm("You have already rated this Dermatologist! Do you want to submit new rate?")) {
          console.log("Implement delete functionality here");
          this.userService.overrideRateDermatologist(this.ratingForm.value).subscribe((data:ApiResponse)=>{
            alert(data.message);
          })
      } else{

      console.log("implement nooooo");
      }
    }
    })

  }


  ratePharmacy() {

    this.pharmacyService.getMyPharmacies().subscribe((data:Pharmacy[])=>{
      this.pharmacies = data;
    }, (error:Error) =>{
      alert(error.errors);
    })


    this.ratePharmacistDiv=false;
    this.rateDermatologistDiv = false;
    this.ratePharmacyDiv=true;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })
  }


  ratePharmacySubmit(){

  }


}
