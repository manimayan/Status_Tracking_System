import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import { Configuration } from "app/app.constants";
import {Employee} from 'app/model/employee'


@Injectable()
export class LoginService {
 
private loggedIn: BehaviorSubject<String> = new BehaviorSubject<String>(null);

get isLoggedIn() {
  return this.loggedIn.asObservable();
}


  private rootUrl:string;

  constructor(private _http: Http, private _config: Configuration) { 
      this.rootUrl = _config.ServerWithApiUrl;
  } 

  login(logindetails:Employee){
      
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ withCredentials: true, headers: headers });
      return this._http.post(this.rootUrl +"employee/search",logindetails,options)        
      .map((res: any) => res.json())
      .catch((err:Response) => {
          return Observable.throw(err);
      });
  }

  }