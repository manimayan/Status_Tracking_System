import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import { Ticket } from "app/model/ticket";
import { Tickethistory } from "app/model/tickethistory";


@Injectable()

export class Tickethistoryservice {

    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}


submit(ticketId,employeeName,days): Observable<Tickethistory[]> {
    console.log("in service");
    console.log(ticketId);
    console.log(employeeName);
    console.log(days);
   

    
    return this._http.get(this.rootUrl + "report/tickethistory/" + ticketId + "/" +employeeName +"/"+ days)
        .map((res: any) => res.json())
       // .catch(this.handleErrorObservable);
    .catch((err:Response) => {
        console.log("after service file");
        return Observable.throw(err);
    });
}


getEmployeeDetails(){
    return this._http.get(this.rootUrl + 'report/getname')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

getticketDetails(){
    return this._http.get(this.rootUrl + 'report/gettickname')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}
}


