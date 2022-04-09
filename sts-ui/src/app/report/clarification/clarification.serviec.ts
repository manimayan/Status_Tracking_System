import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import { Clarify } from "app/model/clarify";
import { Ticket } from "app/model/Ticket";
import { Clarification } from "app/model/Clarification";




@Injectable()
export class clarificationservice {

    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}
clarifydisplay() {
    return this._http.get(this.rootUrl + 'report/clarify')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

clarfication(value:Ticket){
    
 
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'report/search', value, options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}
 
saveComment(comment:Clarification){
 
    console.log(comment.ticket.ticketId)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'report/updateComment', comment, options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
    
    // return this._http.get(this.rootUrl + "timing/timereport/" + id + "/" +fromdate +"/" + todate) 
    
}

deletemethod(ticketId:any,comment:any){
  
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'report/deleteclarify'+'/'+ ticketId+'/'+comment, options)
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