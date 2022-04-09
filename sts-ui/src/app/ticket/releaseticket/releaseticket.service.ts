import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Employee } from 'app/model/employee';
import { Releasetic } from "app/model/releasetic";


@Injectable()
export class ReleaseticketService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}


update(ticketId,releaseTicket)
{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'ticket/update1/' + ticketId + "/" + releaseTicket  ,Option )
    .map((res: any) => res.json())
    .catch((err:Response) => 
    {
        return Observable.throw(err);
    });
    }


getTicketDetails() 
{
    return this._http.get(this.rootUrl + 'ticket/listRelease')
    .map((res: any) => res.json())
    .catch((err:Response) => 
    {
        return Observable.throw(err);
    });
}
}




