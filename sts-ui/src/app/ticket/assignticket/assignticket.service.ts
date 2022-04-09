import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { UploadTicket } from "app/model/UploadTicket";
import { Configuration } from "app/app.constants";
import { Ticket } from "app/model/ticket";
import { BehaviorSubject } from "rxjs/BehaviorSubject";

@Injectable()
export class assignticketService {
    private rootUrl: string;




constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}



getTicketDetails() {
    return this._http.get(this.rootUrl + 'ticket/list')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

getEmployeeDetails() {
    return this._http.get(this.rootUrl + 'ticket/getdevelopername')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });

}




AssignUpdate(employeeId,ticketId,applicationName){    
let headers = new Headers({ 'Content-Type': 'application/json' });
let options = new RequestOptions({ withCredentials: true, headers: headers });
   return this._http.post(this.rootUrl + 'ticket/update/' + employeeId + "/" + ticketId + "/" + applicationName ,Option)
    .map((res: any) => res.json())
    .catch((err:Response) => {
    return Observable.throw(err);
    });
    } 
   
}
     
