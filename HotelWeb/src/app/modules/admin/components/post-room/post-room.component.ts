import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';
import { error } from 'console';

@Component({
  selector: 'app-post-room',
  templateUrl: './post-room.component.html',
  styleUrls: ['./post-room.component.scss']
})
export class PostRoomComponent implements OnInit {

  roomDetailsForm: FormGroup;
  constructor(private fb:FormBuilder,
              private message : NzMessageService,
              private router: Router,
              private adminService:AdminService) {
                this.roomDetailsForm=this.fb.group({
                  name:['',Validators.required],
                  type:['',Validators.required],
                  price:['',Validators.required]
                })
               }

  ngOnInit(): void {
  }

  submitForm(){
     this.adminService.postRoomsDetails(this.roomDetailsForm.value).subscribe(res=>{
       this.message.success(
        'Room Posted Successfully',
        {nzDuration:5000}
       );
       this.router.navigateByUrl('/admin/dashboard')
     },errors=>{
      this.message.error(
        `${errors.error}`,
        {nzDuration:5000}
      )
     })
  }

}
