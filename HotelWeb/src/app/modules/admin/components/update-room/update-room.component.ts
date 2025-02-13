import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';
import { error } from 'console';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent implements OnInit {

  UpdateRoomForm: FormGroup;
  id=this.activatedRoute.snapshot.params['id'];
  constructor(private fb:FormBuilder,
              private message : NzMessageService,
              private router: Router,
              private adminService:AdminService,
              private activatedRoute:ActivatedRoute) {
                this.UpdateRoomForm=this.fb.group({
                  name:['',Validators.required],
                  type:['',Validators.required],
                  price:['',Validators.required]
                });
                this.getRoomById();
               }


  ngOnInit(): void {
  }

  submitForm(){
    this.adminService.UpdateRoomDetails(this.id,this.UpdateRoomForm.value).subscribe(res=>{
      this.message.success(
        `Room Updated Successfully`,
        {nzDuration:5000}
      );
      this.router.navigateByUrl("/admin/dashboard");
    },error=>{
      this.message.error(
        `${error.error}`,
        {nzDuration:5000}
      )
    })

  }

  getRoomById(){
    this.adminService.getRoomById(this.id).subscribe(res=>{
      this.UpdateRoomForm.patchValue(res);
    },error=>{
      this.message.error(`${error.error}`,{nzDuration:5000})
    })
  }
}
