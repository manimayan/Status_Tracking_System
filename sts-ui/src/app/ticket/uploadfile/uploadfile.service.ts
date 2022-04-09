import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { UploadTicket } from "app/model/UploadTicket";
import { Configuration } from "app/app.constants";
import { Ticket } from "app/model/ticket";
@Injectable()
export class uploadfileservice {
    private rootUrl: string;
constructor(private _http: Http, private _config: Configuration) {
    this.rootUrl = _config.ServerWithApiUrl;
 
}
uploadfile(uploadFile:FormData,employeeName)
{   
    console.log(uploadFile)
 
     let headers = new Headers();
    headers.append('Accept', 'application/json');
   let options = new RequestOptions({ withCredentials:true,headers:headers });
    alert("service");
    return this._http.post(this.rootUrl +'ticket/uploadTo/'+employeeName,uploadFile, Option)
    .map((res: any) => res.json())
    .catch((err:Response) => {
    return Observable.throw(err);
    });
    } 



}

