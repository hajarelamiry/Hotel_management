import { Injectable } from '@angular/core';


const TOKEN="token";
const USER="user";
@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }
  static saveToken(token:string):void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }
  static saveUser(user:any):void{
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER,JSON.stringify(user));
  }
  static getToken():string{
    return localStorage.getItem(TOKEN);
  }
  static getUser():any{
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUSerId():string{
    const user=this.getUser();
    if(user==null){return "";}
    return user.id;
  }

  static getUSerRole():string{
    const user=this.getUser();
    if(user==null){return "";}
    return user.role;
  }

  static IsAdminLoggedIn():boolean{
    if(this.getToken===null){
      return false;
    }
    const role:String =this.getUSerRole();
    return role=="ADMIN";
  }

  static IsCustomerLoggedIn():boolean{
    if(this.getToken===null){
      return false;
    }
    const role:String =this.getUSerRole();
    return role=="CUSTOMER";
  }

  static SignOut():void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
