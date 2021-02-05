import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Error } from 'src/app/model/Error';
import { Pharmacy } from 'src/app/model/Pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';

@Component({
  selector: 'app-pharmacy-list',
  templateUrl: './pharmacy-list.component.html',
  styleUrls: ['./pharmacy-list.component.css']
})
export class PharmacyListComponent implements OnInit {
  pharmacies: Pharmacy[];

  sortForm = this.fb.group({
    propertie: ['',Validators.required],
    order: ['' ,Validators.required]
  });

  constructor(private pharmacyService: PharmacyService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.pharmacyService.getAll().subscribe((data: Pharmacy[]) => {
      console.log(data);

      this.pharmacies = data;
    }, 
    (error:Error) => {
      alert(error.errors);
    })
  }


  sortPharmacies(){

    console.log(this.sortForm.value);
    this.pharmacyService.sortPharmacies(this.sortForm.controls["propertie"].value , this.sortForm.controls["order"].value )
    .subscribe( (data:Pharmacy[]) => {
      console.log(data);
      this.pharmacies = data; 
    }, 
    (error:Error) => {
      alert(error.message);
    })
    
  }

  goToPharmacyProfile(pharmacyId) {

    //router navigate bla bla pharmacyId
    this.router.navigate([`/pharmacyProfile/${pharmacyId}`]);
  }

}
