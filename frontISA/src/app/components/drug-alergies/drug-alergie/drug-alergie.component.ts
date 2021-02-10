import { Component, OnInit } from '@angular/core';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Drug } from 'src/app/model/Drug';
import { Error } from 'src/app/model/Error';
import { DrugService } from 'src/app/services/drug-service/drug.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-drug-alergie',
  templateUrl: './drug-alergie.component.html',
  styleUrls: ['./drug-alergie.component.css']
})
export class DrugAlergieComponent implements OnInit {
  drugs: Drug[];
  drugsAlergie: Drug[];
  constructor( private drugService: DrugService , private userService : UserService) { }

  ngOnInit() {

    this.drugService.getAll().subscribe( (data:Drug[]) => {
     console.log(data);
      this.drugs = data;
    })

    this.drugService.getMyAlergieList().subscribe( (data:Drug[]) => {
      this.drugsAlergie = data;

    })
  }

  selectedDrug(drugId){
    
    this.drugService.addToAlergieList(drugId).subscribe( (data:Drug[])=>{
      alert("Drug added")
      this.drugsAlergie = data;
     
    }, (error: Error) => {
      alert(error.errors);
    })

  }

  removeDrug(drugId) {

    this.drugService.removeDrugFromAlergieList(drugId) .subscribe((data:Drug[])=>{
      alert("Drug removed from list");
      this.drugsAlergie =data;
    }, (error:Error ) =>{
      alert(error);
    })
  }

}
