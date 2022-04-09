import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Employee } from "app/model/employee";
import { Timereport } from "app/model/timereport";


@Injectable()
export class TimereportService {
    handleErrorObservable(arg0: any): any {
        throw new Error("Method not implemented.");
    }
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}
getdetails(){
    return this._http.get(this.rootUrl + 'timing/getname/')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

/*submitempdetails(id,fromdate,todate): Observable<Timereport[]> {
    return this._http.get(this.rootUrl + "timing/timereport/" + id + "/" +fromdate +"/" + todate)
        .map((res: any) => res.json())
    .catch((err:Response) => {
        console.log("after service file");
        return Observable.throw(err);
    });
}*/

getNotReportedAbsence(){
    console.log("before status service")
    return this._http.get(this.rootUrl + 'report/listnotrepabsence')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}


getnotrepoertedEmployee() {
    return this._http.get(this.rootUrl + 'report/notreporttimelist')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
}

submitempdetails(value) {
    console.log("in service");
    console.log(value.employeeName);
    console.log(value.employeeId)
    console.log(value.fdate);
    console.log(value.todate);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
   // return this._http.get(this.rootUrl + "timing/timereport/" + employeeName + "/" +fromDate +"/" + toate)
    return this._http.post(this.rootUrl + "timing/timereport",value,options)

        .map((res: any) => res.json())
       // .catch(this.handleErrorObservable);
    .catch((err:Response) => {
        console.log("after service file");
        return Observable.throw(err);
    });
}

}