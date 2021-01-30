import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {
  restUrl = 'http://localhost:8080/api/pharmacyController/';

  constructor( private http: HttpClient) { }


  getAll(){
    return this.http.get( `${this.restUrl}pharmacies` );
  }

  searchPharm(params) {
    
    return this.http.get(`${this.restUrl}searchPharm/?${params}` );


    
  }

}
