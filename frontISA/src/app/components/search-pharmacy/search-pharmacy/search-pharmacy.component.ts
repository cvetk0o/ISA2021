import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormControlName } from '@angular/forms';
import { Pharmacy } from 'src/app/model/Pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';
import {URLSearchParams} from '@angular/http';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-search-pharmacy',
  templateUrl: './search-pharmacy.component.html',
  styleUrls: ['./search-pharmacy.component.css']
})
export class SearchPharmacyComponent implements OnInit {
  pharmacies: Pharmacy[];

  rate = new FormControl();

  searchForm = this.fb.group({
    name :[''],
    city : [''],
    consultingPriceFrom :[''],
    consultingPriceTo :['']
  });


  constructor(private pharmacyService: PharmacyService,
              private fb: FormBuilder
    )  { }

  ngOnInit() {
    this.pharmacyService.getAll().subscribe((data: Pharmacy[]) => {
      console.log(data);

      this.pharmacies = data;
    })
  }

  search() {

    let params = new HttpParams();
    const formValue = this.searchForm.value; // this.form should be a FormGroup
  

    for (const key in formValue) {
      if(formValue[key] !="") {
          if(key === 'city' ){
            let kljuc = "address.city";
            params = params.append( kljuc, formValue[key]);

          }else if (key ==="consultingPriceFrom"){
            let kljuc = "consultingPrice";
            params = params.append( kljuc, formValue[key]);
            
          }else if (key ==="consultingPriceTo"){
            let kljuc = "consultingPrice";
            params = params.append( kljuc, formValue[key]);
          }else {

            params = params.append(key , formValue[key]);
          }
        }
    }
    console.log(params);  

    this.pharmacyService.searchPharm(params).subscribe( (data: Pharmacy[] ) => {
      this.pharmacies = data;
      
    })
    
  }

  filterPharms() {
    let params = new HttpParams();
    const formValue = this.searchForm.value; // this.form should be a FormGroup
  

    for (const key in formValue) {
      if(formValue[key] !="") {
          if(key === 'city' ){
            let kljuc = "address.city";
            params = params.append( kljuc, formValue[key]);

          }else
            params = params.append(key , formValue[key]);
        }
    }
    console.log(params);  
    params = params.append("avgRate" , this.rate.value);
    this.pharmacyService.searchPharm(params).subscribe( (data: Pharmacy[] ) => {
      this.pharmacies = data;
      
    })
  }
}
