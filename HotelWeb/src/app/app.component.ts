import { Component } from '@angular/core';
import { UserStorageService } from './auth/services/storage/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'HotelWeb';
  isCustomerLoggedIn:boolean=UserStorageService.IsCustomerLoggedIn();
  isAdminLoggedIn:boolean=UserStorageService.IsAdminLoggedIn();

  constructor(private router:Router){}

  ngOnInit(){
    this.router.events.subscribe(event=>{
      if(event.constructor.name === "NavigationEnd"){
        this.isCustomerLoggedIn=UserStorageService.IsCustomerLoggedIn();
        this.isAdminLoggedIn=UserStorageService.IsAdminLoggedIn();
      }
    })
  }

  logout(){
    UserStorageService.SignOut();
    this.router.navigateByUrl('/');
  }


}
