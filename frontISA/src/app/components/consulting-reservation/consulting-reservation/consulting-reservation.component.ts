import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Error } from 'src/app/model/Error';
import { Pharmacy } from 'src/app/model/Pharmacy';
import { User } from 'src/app/model/User';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';

@Component({
  selector: 'app-consulting-reservation',
  templateUrl: './consulting-reservation.component.html',
  styleUrls: ['./consulting-reservation.component.css']
})
export class ConsultingReservationComponent implements OnInit {
  reservation: any = {};
  dateControl = new FormControl(new Date , Validators.required);
  timeControl = new FormControl(new Date , Validators.required);

  availablePharmacies: Pharmacy[];
  availablePharmacists: User[];

  pharmacyDiv = false;
  pharmacistDiv = false;


  sortForm = this.fb.group({
    propertie: ['',Validators.required],
    order: ['' ,Validators.required]
  });

  sortPharmacistForm = this.fb.group({
    propertie: [''],
    order: ['' ,Validators.required]
  });
  
  constructor(private fb: FormBuilder , private pharmacyService: PharmacyService) { }

  ngOnInit() {
  }

  valuechangeDate( newValue) {
    console.log(this.dateControl.value);
  }

  valuechange( newValue) {
    console.log(this.timeControl.value);
  }

  prvoFiltriranje(){
    if(this.pharmacistDiv == true) 
      this.pharmacistDiv = false;

    this.reservation.date = this.dateControl.value;
    this.reservation.time = this.timeControl.value;

    this.pharmacyService.getAvailablePharmacies(this.reservation).subscribe( (data:Pharmacy[]) => {
      console.log(data);
      this.availablePharmacies = data;
      this.pharmacyDiv = true;
    },
    (err : Error)=> {
      alert(err.errors)
        this.pharmacyDiv = false;
    }
    )
  }

  showPharmacists(pharmacyId) {
    this.reservation.pharmacyId = pharmacyId;
    this.pharmacyService.showPharmacists(this.reservation) . subscribe( (data:User[]) => {
      console.log(data);
      this.availablePharmacists = data;
      this.pharmacistDiv=true;
    })
  }


  makeReservation( pharmacistId) {
    this.reservation.pharmacistId = pharmacistId;
    this.pharmacyService.makeReservation(this.reservation).subscribe( (data:ApiResponse) => {
      alert(data.message)
      
    },(error:Error) =>{
      alert(error.errors);
    }
    )
  }


  sortPharmacies(){

    console.log(this.sortForm.value);
    this.pharmacyService.sortAvailablePharmacies(this.sortForm.controls["propertie"].value , this.sortForm.controls["order"].value  , this.reservation)
    .subscribe( (data:Pharmacy[]) => {
      console.log(data);
      this.availablePharmacies = data; 
    }, 
    (error:Error) => {
      alert(error.message);
    })
    
  }

  sortPharmacists() {
     if(this.sortPharmacistForm.get("order").value === "ascending"){
    this.availablePharmacists= this.availablePharmacists.sort((p,q)=> (p.avgRate > q.avgRate) ? 1 : -1 )
     }else {
      this.availablePharmacists= this.availablePharmacists.sort((p,q)=> (p.avgRate > q.avgRate) ? -1 : 1 )
     }
  console.log(this.availablePharmacists);
  
  }

}
