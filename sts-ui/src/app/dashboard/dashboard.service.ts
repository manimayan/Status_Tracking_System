import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";


@Injectable()
export class DashboardService {

    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getworkprogress(employeeId:any)
{

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'ticket/getworkprogress'+'/'+ employeeId , options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
    
    }

getplannedvacation(employeeId:any)
{

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'vacation/getplannedvacationmonths'+'/'+ employeeId , options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
    
    }

getunplannedvacation(employeeId:any)
{

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'vacation/getunplannedvacationmonths'+'/'+ employeeId , options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
    
    }

}

