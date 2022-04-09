 import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";

import { Ticket } from "app/model/ticket";

@Injectable()
export class EditpriorityService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getPriorityDetails(empId:any) {
    
    return this._http.get(this.rootUrl + 'ticket/listpri'+'/'+ empId)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}
update(empId:any,ticketId,newPriority){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    console.log("in service");console.log(newPriority);
       return this._http.post(this.rootUrl + "ticket/updatepri" + "/" + empId +"/"+ ticketId + "/" + newPriority ,options)
        .map((res: any) => res.json())
      
    .catch((err:Response) => {
        console.log("after service file");
        return Observable.throw(err);
        
    });
       
}
checkeditpriority(employeeId:any)
{
    
    console.log("in service")
   // console.log(value)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
   // return this._http.post(this.rootUrl + 'ticket/save/' ,value,options)
     return this._http.post(this.rootUrl + 'ticket/checkeditpriority'+'/'+  employeeId , options)
    
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
    
    }
}

