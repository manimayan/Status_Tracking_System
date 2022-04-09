import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Timing } from 'app/model/timing';

@Injectable()
export class OuttimeService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}


getOuttimeDetails(value:Timing) {
    console.log(value)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    console.log(this.rootUrl);
    return this._http.post(this.rootUrl + 'timing/outTime/' ,value,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
} 



}