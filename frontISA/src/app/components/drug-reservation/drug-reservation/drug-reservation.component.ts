import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Drug } from 'src/app/model/Drug';
import { DrugSearchDTO } from 'src/app/model/DrugSearchDTO';
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

  dateDiv = false;

  constructor( private drugService: DrugService , private pharmacyService: PharmacyService  ) { }

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
      this.drugService.makeDrugReservation(JSON.stringify(this.reservation) ).subscribe( data=> {
        console.log(data);
      })
  }

}
