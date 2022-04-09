import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import { Employee } from "app/model/employee";
import { Security } from "app/model/security";
import { Question } from "app/model/question";

@Injectable()
export class SecurityService {
    private rootUrl: string;
    public questionAns: Question[];

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getSecurityDetails() {
    return this._http.get(this.rootUrl + 'employee/getquestion')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}


saveDetails(ans){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });;
    this.questionAns=ans;
    return this._http.post(this.rootUrl +"employee/getdetails",ans,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
  }
}