import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { error } from 'console';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  currentPage=1;
  rooms=[];
  total:any;
  loading=false;
  constructor(private adminService:AdminService,
              private message:NzMessageService,
              private modalservice:NzModalService) {
                this.getRooms();
               }

  ngOnInit(): void {
  }

  getRooms(){
    this.adminService.getRooms(this.currentPage -1).subscribe(res=>{
      console.log(res);
      this.rooms=res.roomDtoList;
      this.total=res.totalpages*1;
    })
  }

  pageIndexChange(value:any){
    this.currentPage=value
    this.getRooms();
  }

  showConfirm(id:number){
    this.modalservice.confirm({
      nzTitle:"Confirm",
      nzContent:"Do you want to delete this room?",
      nzOkText:"Delete",
      nzCancelText:"cancel",
      nzOnOk:()=>this.DeleteRoom(id)
    })
  }
   
  DeleteRoom(id:number){
    this.adminService.DeleteRoom(id).subscribe(res=>{
      this.message.success(
        `Room Deleted Successfully`,
      {nzDuration:5000}
      );
      this.getRooms();
    },error=>{
      this.message.error(`${error.error}`,{nzDuration:5000});
    })
  }

}
