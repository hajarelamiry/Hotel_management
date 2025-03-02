import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { error } from 'console';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup;
  constructor(private fb:FormBuilder,
              private authService: AuthService,
              private message:NzMessageService,
              private router:Router,) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      password:[null,[Validators.required]]
    })
  }
  submitForm(){
    this.authService.login(this.loginForm.value).subscribe(res=>{
    console.log(res);
    if(res.userId != null){
      const user={
        id:res.userId,
        role:res.userRole
      }
      UserStorageService.saveUser(user);
      UserStorageService.saveToken(res.jwt);
      if(UserStorageService.IsAdminLoggedIn()){
        this.router.navigateByUrl('/admin/dashboard');
      }else if(UserStorageService.IsCustomerLoggedIn()){
        this.router.navigateByUrl('/customer/rooms');
      }
      }
    },error=>{
      this.message.error("Bad Credentials",{nzDuration:5000})
    })
  }

}
