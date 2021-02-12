import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Drug } from 'src/app/model/Drug';
import { Error } from 'src/app/model/Error';
import { Pharmacy } from 'src/app/model/Pharmacy';
import { User } from 'src/app/model/User';
import { DrugService } from 'src/app/services/drug-service/drug.service';
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
  rateDrugDiv = false;

  ratingForm = this.fb.group({
    id : ['',Validators.required],
    rating: ['' ,Validators.required]
  });

  pharmacists: User[];
  dermatologists: User[];
  pharmacies: Pharmacy[];
  drugs: Drug[];

  constructor(private fb: FormBuilder,private pharmacyService: PharmacyService , private userService: UserService , private drugService: DrugService) { }

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
    this.rateDrugDiv= false;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })

  }


  ratePharmacistsSubmit() {
    console.log(this.ratingForm.value);
    
    this.userService.ratePharmacist(this.ratingForm.value).subscribe((data:ApiResponse) => {
      alert(data.message);
      
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
    this.rateDrugDiv= false;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })
  }

  rateDrugs() {

    this.drugService.getMyDrugs().subscribe((data:Drug[])=>{
      console.log(data);
      this.drugs =data;

    }, (error:Error) =>{
      alert(error.errors)
    })
   
    this.ratePharmacistDiv=false;
    this.rateDermatologistDiv = false;
    this.ratePharmacyDiv=false;
    this.rateDrugDiv= true;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })



  }
  
  rateDrugSubmit(){
  
    this.drugService.rateDrug(this.ratingForm.value).subscribe((data: ApiResponse) => {
      alert(data.message);
      
    }, (error:Error) => {

      if(error.errors[0] === "You have already rated this drug") {
        if(confirm("You have already rated this Drug! Do you want to submit new rate?")) {
          console.log("Implement delete functionality here");
          this.drugService.overrideRateDrug(this.ratingForm.value).subscribe((data:ApiResponse)=>{
            alert(data.message);
          })
      } else{

      console.log("implement nooooo");
      }
    }
    })

  }



  rateDermatologistSubmit(){
  
    this.userService.rateDermatologist(this.ratingForm.value).subscribe((data: ApiResponse) => {
      alert(data.message);
      
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
    this.rateDrugDiv= false;

    this.ratingForm.patchValue({
      id: '',
      rating: ''
    })
  }


  ratePharmacySubmit(){


    this.pharmacyService.ratePharmacy(this.ratingForm.value).subscribe((data: ApiResponse) => {
      alert(data.message);
      
    }, (error:Error) => {

      if(error.errors[0] === "You have already rated this pharmacy") {
        if(confirm("You have already rated this Pharmacy! Do you want to submit new rate?")) {
          console.log("Implement delete functionality here");
          this.pharmacyService.overrideRatePharmacy(this.ratingForm.value).subscribe((data:ApiResponse)=>{
            alert(data.message);
          })
      } else{

      console.log("implement nooooo");
      }
    }
    })

  }


}
