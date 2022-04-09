import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";


@Injectable()
export class NotreportedtimeService {
    handleErrorObservable(arg0: any): any {
        throw new Error("Method not implemented.");
    }
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}
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

}