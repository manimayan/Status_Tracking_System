import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import { Ticket } from "app/model/ticket";



@Injectable()
export class AddticketService {

    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getAppname()
{
    return this._http.get(this.rootUrl + "ticket/getname")
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });    
}

saveAddticket(newaddedlist: Ticket,appId,employeeId){
    let headers = new Headers({ 'Content-Type': 'application/json' });
   let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'ticket/Add/'+appId+'/'+employeeId,newaddedlist,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });}


}