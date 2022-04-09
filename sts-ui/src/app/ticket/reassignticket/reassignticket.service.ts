import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Reassignticket } from "app/model/reassignticket";
import { Ticket } from "app/model/ticket";
import { Employee } from "app/model/employee";


@Injectable()
export class ReassignticketService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getReassignDetails() {
    console.log("before service file");
    return this._http.get(this.rootUrl + 'ticket/listdetails')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        console.log("after service file");
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

ReassignUpdate(employeeId,ticketId)
{   
    
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'ticket/reassignticket/' + employeeId + "/" + ticketId,Option)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
    }
}
