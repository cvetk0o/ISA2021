import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DrugService {
  restUrl = 'http://localhost:8080/api/drugController/';



  constructor( private http: HttpClient) { }


  getAll() {
    return this.http.get(`${this.restUrl}drugs`);
  }

  getPharmacies(id) {

    return this.http.get(`${this.restUrl}drugs/${id}/pharmacies`)
  }

  makeDrugReservation( reservation) {

    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post(`${this.restUrl}drugs/reservation` , reservation , httpOptions);
  }

  addToAlergieList(drugId) {
    return this.http.post(`${this.restUrl}drugs/alergie`,drugId )
  }

  removeDrugFromAlergieList(drugId) {
    return this.http.get(`${this.restUrl}drugs/alergies/${drugId}` )
  }

  getMyAlergieList() {
    return this.http.get(`${this.restUrl}drugs/alergies`)
  }

  rateDrug(data) {
    return this.http.post(`${this.restUrl}rateDrug` , data);
  }

  overrideRateDrug(data) {
    return this.http.post(`${this.restUrl}overrideRateDrug` , data);
  }

  getMyDrugs() {
    return this.http.get(`${this.restUrl}getMyDrugs`);
  }

  
  searchDrugs(params) {
    
    return this.http.get(`${this.restUrl}searchDrugs/?${params}` );

  }

}
