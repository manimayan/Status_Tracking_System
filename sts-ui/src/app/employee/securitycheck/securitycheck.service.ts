import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import { Employee } from "app/model/employee";

@Injectable()
export class SecuritycheckService {
    private rootUrl: string;
constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}


employeeRole(employeeId)
{  
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ withCredentials: true, headers: headers });
        return this._http.post(this.rootUrl + 'employee/getmodify/' + employeeId,Option)
        .map((res: any) => res.json())
        .catch((err:Response) => {
        return Observable.throw(err);
    });
}

getSecurityDetails() {
    return this._http.get(this.rootUrl + 'employee/getquestion')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

answer(ans){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });;
    return this._http.post(this.rootUrl +"employee/getanswer",ans,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
  }

getQuestionDetails(empId:any) {
    
    return this._http.get(this.rootUrl + 'employee/listquestion'+'/'+ empId)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}


saveanswer(ans1){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });;
    return this._http.post(this.rootUrl +"employee/getSaveans",ans1,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
  }

getAdminDetails() {
    return this._http.get(this.rootUrl + 'employee/admin')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

}