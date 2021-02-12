import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Drug } from 'src/app/model/Drug';
import { DrugSearchDTO } from 'src/app/model/DrugSearchDTO';
import { Error } from 'src/app/model/Error';
import { DrugService } from 'src/app/services/drug-service/drug.service';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-drug-reservation',
  templateUrl: './drug-reservation.component.html',
  styleUrls: ['./drug-reservation.component.css']
})
export class DrugReservationComponent implements OnInit {
  dateStringControl = new FormControl(new Date());
  drugs: Drug[];
  items: DrugSearchDTO[];
  reservation: any = {};

  drugId;

  som=false;

  dateDiv = false;


  searchForm = this.fb.group({
    name :[''],
    type : ['']
  });


  constructor( private fb:FormBuilder,private drugService: DrugService , private pharmacyService: PharmacyService  ) { }

  ngOnInit() {

    this.drugService.getAll().subscribe( (data:Drug[]) => {
     console.log(data);
      this.drugs = data;
    })
  }


  selectedDrug(id) {
    if( this.dateDiv == true)
     { 
       this.dateDiv = false;
        this.reservation = {};
     }
    this.reservation.drugId = id;
    this.drugService.getPharmacies(id).subscribe( (data:DrugSearchDTO[])  => {
      this.items = data;
    })

  }

  selectedPharmacy(itemId, pharmacyId) {
    this.reservation.itemId = itemId;
    this.reservation.pharmacyId = pharmacyId;

    if(this.dateDiv == false) 
      this.dateDiv = true;
    else
      this.reservation.reservedAt = null;
  }


  valuechange( newValue) {
    this.reservation.reservedAt = this.dateStringControl.value;
  }

  makeReservation() {
  
      console.log(this.reservation);
      this.drugService.makeDrugReservation(JSON.stringify(this.reservation) ).subscribe( (data:ApiResponse)=> {
        alert(data.message)
        window.location.reload();
      } ,(error:Error) =>{
        alert(error.errors);
      })
  }



  
  search() {

    let params = new HttpParams();
    const formValue = this.searchForm.value; // this.form should be a FormGroup
  

    for (const key in formValue) {
      if(formValue[key] !="") {
 
            params = params.append(key , formValue[key]);
        }
    }
    console.log(params);  

    this.drugService.searchDrugs(params).subscribe( (data: Drug[] ) => {
      this.drugs = data;
      
    })
    
  }

}
