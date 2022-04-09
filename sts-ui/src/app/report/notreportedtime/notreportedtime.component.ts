import { Component, OnInit } from '@angular/core';

import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";
import { NotreportedtimeService } from "app/report/notreportedtime/notreportedtime.service";
import { DatePipe } from '@angular/common'; 

@Component({
  selector: 'app-notreportedtime',
  templateUrl: './notreportedtime.component.html',
  styleUrls: ['./notreportedtime.component.css'],providers:[NotreportedtimeService,,DatePipe]
})
export class NotreportedtimeComponent implements OnInit {
    notreportedabsence: any;
    nottimeReport: any;
    subscription: Subscription;
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    constructor(private service:NotreportedtimeService ,private datePipe: DatePipe,public nav: NavbarService ) { 
        this.subscription=this.nav.getDumpdetails().subscribe(
                value => { 
                         this.Dumpdetails = value; 
                         });
         }
    ngOnInit(){
        this.getdumpdetails();
        this.getNotReportedAbsence();
        this.getnotrepoertedEmployee();
    }
    
    getdumpdetails()
    {
        this.createdBy= this.nav.Dumpdetails.getValue().createdBy
        this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
    }
    
    getNotReportedAbsence(){
        
        this.service.getNotReportedAbsence()
        .subscribe(
        res=>{
            this.notreportedabsence=res;
            console.log(this.notreportedabsence)
        });
       
    }
    
    
    getnotrepoertedEmployee() {
        this.service.getnotrepoertedEmployee()
            .subscribe(
                    res => {
                        this.nottimeReport = res;
                        console.log(this.nottimeReport) 
                    }); 
    }
    }
    
    
   
   

