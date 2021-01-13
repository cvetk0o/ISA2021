import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

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
            case "PATIENT":
            this.router.navigate(["/patientHomePage"]);
              break;
          case "PHARMACY_ADMIN":
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
}