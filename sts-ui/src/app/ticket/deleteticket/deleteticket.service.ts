import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { DeleteTicket } from "app/model/DeleteTicket";
import { Ticket } from "app/model/ticket";


@Injectable()
export class DeleteticketService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getTicketDetails() {
    return this._http.get(this.rootUrl + 'ticket/listdel')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

deleteTick(ticketid) {
    
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
       return this._http.post(this.rootUrl + "ticket/delete/"  +  ticketid ,options)
        .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

}