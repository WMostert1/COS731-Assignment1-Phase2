import { Injectable } from '@angular/core';
import { User } from './user'
import {HttpClient} from '@angular/common/http';
import { Observable } from "rxjs/Observable";

@Injectable()
export class TransactionService {
    constructor(private http: HttpClient) {}

    getUser(email : String) : Observable<User> {
        return this.http.get<User>('/api/user?email=' + email);
    }

    register(email : string, regNo: string){
         this.http.post(`/api/user?email=${email}&carRegistration=${regNo}`,'').subscribe((response : string)=>{
            console.log(response)
        });
    }

    updateCars(regNo: string [], email : string) {
        let queryString = '';
        for(let r of regNo)
            queryString = queryString + '&carRegistration='+r;


        queryString = 'email='+email+queryString;     

        this.http.put('/api/car?'+queryString, '').subscribe((response : string)=>{
            console.log(response)
        });
    }
}