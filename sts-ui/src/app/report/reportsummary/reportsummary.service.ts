import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Reportsummary } from "app/model/reportsummary";


@Injectable()
export class ReportsummaryService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

//dynamic dropdown for fetching employeeName
getEmployeeDetails() {
        return this._http.get(this.rootUrl + 'report/getempname')
        .map((res: any) => res.json())
        .catch((err:Response) => {
            return Observable.throw(err);
      });
}

//dynamic dropdown for fetching applicationName
getApplicationDetail() {
        return this._http.get(this.rootUrl + 'report/getappname')
        .map((res: any) => res.json())
        .catch((err:Response) => {
            return Observable.throw(err);
    });
}

//dynamic dropdown for fetching tester
getTestername(){
        return this._http.get(this.rootUrl + 'report/gettestername')
        .map((res: any) => res.json())
        .catch((err:Response) => {
            return Observable.throw(err);
    });
}

dayticketdetails(value)
{   
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ withCredentials: true, headers: headers });
        return this._http.post(this.rootUrl + 'report/reportlist/',value,Option)
        .map((res: any) => res.json())
        .catch((err:Response) => {
        return Observable.throw(err);
    });
    }

}

