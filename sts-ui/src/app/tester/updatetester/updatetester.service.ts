import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

import { Configuration } from "app/app.constants";
import { Updatestatus } from "app/model/updatestatus";


@Injectable()
export class UpdatetesterService {
    handleErrorObservable(arg0: any): any {
        throw new Error("Method not implemented.");
    }
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}
getAppdetails(){
    return this._http.get(this.rootUrl + 'ticket/getapplication')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}

getTestename(){
    return this._http.get(this.rootUrl + 'ticket/gettestername')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}
/*getdetails(value:Updatestatus){
    return this._http.get(this.rootUrl + 'home/list/')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        console.log("after service file");
        return Observable.throw(err);
    });
    }*/

savetesterdetails(value:Updatestatus){
    console.log("in service tester")
    console.log(value)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ withCredentials: true, headers: headers });
    return this._http.post(this.rootUrl + 'ticket/savetester/' ,value,options)
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
        
    });
    }

}







