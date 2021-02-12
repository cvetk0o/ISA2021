import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JWToken } from 'src/app/model/JWToken';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  patientDiv = false;
  constructor(private authService: AuthService,
              private router: Router
    
      ) { }

  ngOnInit() {
  }


  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  signedIn() {
    return this.authService.currentUserValue != null;
  }


  checkHome() {
    
    if( this.authService.currentUserValue != null ) {
          switch (this.authService.currentUserValue.role) {
            case "ROLE_PATIENT":
            this.router.navigate(["/patientHomePage"]);
              break;
          case "ROLE_PHARMACY_ADMIN":
            this.router.navigate(["/pharmacyAdminHomePage"]);
              break;
          default:
            this.router.navigate(["/"]);
              break;
      } 
    } else {
      this.router.navigate(["/"])
    }
}


patient() {
  var nesto : JWToken = this.authService.currentUserValue;

  return nesto.role ==="ROLE_PATIENT";
  
  
}
}