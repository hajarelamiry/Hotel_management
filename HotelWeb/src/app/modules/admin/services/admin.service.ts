import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';


const BASIC_URL="http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  postRoomsDetails(roomDto: any):Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
  });

    return this.http.post(BASIC_URL+"api/admin/room", roomDto, { headers });
  }

  getRooms(pageNumber:number):Observable<any>{
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
      return this.http.get(BASIC_URL+`api/admin/rooms/${pageNumber}`,{ headers });
  }
}
