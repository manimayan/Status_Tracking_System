import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Documentation } from "app/model/documentation";

@Injectable()
export class DocumentationService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

saveDocument(newaddedlist: Documentation,ticket_id) {
    console.log(newaddedlist)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });;
    return this._http.post(this.rootUrl + 'ticket/save/' +ticket_id ,newaddedlist,options )
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });}


getClosedDetails(empId:any) {
    return this._http.get(this.rootUrl + 'ticket/homedoc'+'/'+ empId)
    
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
} 





}