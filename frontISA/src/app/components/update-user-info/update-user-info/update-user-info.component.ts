import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-update-user-info',
  templateUrl: './update-user-info.component.html',
  styleUrls: ['./update-user-info.component.css']
})
export class UpdateUserInfoComponent implements OnInit {

  
  updateForm = this.fb.group({
    name: ['', Validators.required],
    lastname: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    city: ['', Validators.required],
    street: ['', Validators.required],
    country: ['', Validators.required],
    number: ['' , Validators.required]
  });
  constructor( private fb: FormBuilder , private userService: UserService) { }

  ngOnInit() {
    this.userService.getMyInfo().subscribe( (data:User) => {
      console.log(data);
      this.updateForm.patchValue( {
        name : data.name,
        lastname : data.lastname,
        city: data.address.city,
        country: data.address.country,
        number : data.address.number,
        phoneNumber : data.phoneNumber,
        street : data.address.street

      })
    })
  }



  update() {

    console.log(this.updateForm);
    
      this.userService.updateMyInfo(this.updateForm.value). subscribe( data => {
        console.log(data);
      })
  }


}
