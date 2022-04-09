import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

@Injectable()
export class Configuration {
    public server: string = '';
    public apiUrl: string = 'sts-web/';
    public ServerWithApiUrl = "";
    
    public constructor(){
        if(environment.local){
            this.server = 'http://localhost:9090/';
            this.ServerWithApiUrl = this.server + this.apiUrl;
        } else if(environment.dev) {
            this.server = 'http://localhost:9090/';
            this.ServerWithApiUrl = this.server + this.apiUrl;
        } else {
            this.ServerWithApiUrl = '';
        }
    }
}