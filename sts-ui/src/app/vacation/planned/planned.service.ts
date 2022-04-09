import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import * as FileSaver from 'file-saver';
import { Configuration } from "app/app.constants";
import { Vacation } from 'app/model/Vacation';

@Injectable()
export class PlannedVacationService {
    private rootUrl: string;

constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
}

getPlannedVacationDetails() {
    return this._http.get(this.rootUrl + 'vacation/listPlanned')
    .map((res: any) => res.json())
    .catch((err:Response) => {
        return Observable.throw(err);
    });
}


}