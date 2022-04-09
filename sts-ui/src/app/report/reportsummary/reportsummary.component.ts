import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from "app/model/employee";

import { ReportsummaryService } from "app/report/reportsummary/reportsummary.service";
import { Application } from "app/model/application";
import { SelectItem } from "primeng/primeng";

import { DatePipe } from '@angular/common'; 
import { Reportsummary } from "app/model/reportsummary";
import {MessageModule} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
import { NavbarService } from 'app/navbar.service';
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-reportsummary',
  templateUrl: './reportsummary.component.html',
  styleUrls: ['./reportsummary.component.css'],
providers :[ ReportsummaryService,DatePipe ],
})
export class ReportsummaryComponent implements OnInit {
    subscription: Subscription;
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    public msgs: MessageModule[] = [];
    public empList:any[]=[];
    public appList:any[]=[];
    public testerName:any []=[];
    public display:any[]=[];
    
  //static dropdown
    type: SelectItem[];
    release: SelectItem[];
    act: SelectItem[];
    statuses: SelectItem[]; 
   
    private eName: Employee[] = [];
    private appname: Application[] = [];
    private dayticket: Reportsummary[] = [];
    private timeReport: Reportsummary[] = [];
    public showReport : boolean;


  constructor(private service:ReportsummaryService,private datePipe: DatePipe, public nav: NavbarService) {
      
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
      this.type = [
                   {label: '', value: null},
                   {label: 'Project', value: 'Project'},
                   {label: 'Support', value: 'Support'},
                   {label: 'Enhancement', value: 'Enhancement'},
               ];
      
//static dropdown for releaseTicket
     this.release = [
                   {label: '', value: null},
                   { label: 'Yes', value: 'Yes' },
                   { label: 'No', value: 'No' }
               ];

 //static dropdown for activity     
     this.act =  [
                   {label: '', value: null},
                   {label: 'Analysis', value: 'Analysis'},
                   {label: 'Build', value: 'Build'},
                   {label: 'Deployment', value: 'Deployment'},
                   {label: 'Design', value: 'Design'},
                   {label: 'Documentation', value: 'Documentation'},
                   {label: 'Estimation', value: 'Estimation'},
                   {label: 'Testing', value: 'Testing'},
                   {label: 'UAT', value: 'UAT'}
               ];

  //static dropdown for status     
     this.statuses = [
                   {label: '', value: null},
                   {label: 'Inprogress', value: 'Inprogress'},
                   {label: 'OnHold', value: 'OnHold'},
                   {label: 'Approved', value: 'Approved'},
                   {label: 'Completed', value: 'Completed'},
                   {label: 'Closed', value: 'Closed'},
                   {label: 'NotStarted', value: 'NotStarted'}
               ]; 
  }

  ngOnInit() {
      this.nav.show();
      this.getdetails();
      this.getappdetail();
      this.getdumpdetails();
      this.getTestername();
      this.showReport=false;
  }

  
//dynamic dropdown for fetching employeeName
  getdetails(){
      this.service.getEmployeeDetails()
      .subscribe(
              res => {
                  this.eName=res;
                  this.empList.push({'label' : '','value': null});
                  this.eName.forEach(mod => {
                      this.empList.push({'label' : mod. employeeName,'value': mod.employeeId});
                  })
              });
} 
 
// dynamic dropdown for fetching applicationName
  getappdetail(){
      this.service.getApplicationDetail()
      .subscribe(
              res => {
                  this.appname=res;
                  this.appList.push({'label' : '','value': null});
                  this.appname.forEach(mod => {
                      this.appList.push({'label':mod.applicationName,'value' : mod.applicationId});
                  })
              });
  }
  
//dynamic dropdown for fetching tester
  getTestername(){
      console.log("in dispalying tester name");
      this.service.getTestername()
      .subscribe(
              res => {
                  this.testerName=res;
                  this.display.push({'label' : '','value': null});
                  this.testerName.forEach(mod => {
                      this.display.push({'label' : mod. employeeName,'value': mod.employeeName});     
                  })
              });    
  }
  
  Onclick(event){
      this.showReport=true;
  }
    
  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  
 //function to fetch the values for p-datatable by joining 5 tables
  dayticketdetails(value:Reportsummary) {
          value.startdateFrom= this.datePipe.transform(value.startdateFrom,'yyyy-MM-dd'); 
          value.startdateTo= this.datePipe.transform(value.startdateTo,'yyyy-MM-dd');
          value.enddateFrom= this.datePipe.transform(value.enddateFrom,'yyyy-MM-dd'); 
          value.enddateTo= this.datePipe.transform(value.enddateTo,'yyyy-MM-dd');
        
          if( value.startdateFrom > value.startdateTo){
              this.msgs.push({ severity: 'error', summary: '', detail: "Enter Valid Date Range " });
          }
          else if( value.enddateFrom > value.enddateTo){
              this.msgs.push({ severity: 'error', summary: '', detail: "Enter Valid Date Range " });
          }
          else if( value.startdateTo > value.enddateFrom){
              this.msgs.push({ severity: 'error', summary: '', detail: "Enter Valid Date Range " });
          }
          else  if(value.startdateFrom !=null && value.startdateTo===null){
              this.msgs.push({ severity: 'error', summary: '', detail: "Start Date fields should not be empty" });
          }
          else  if(value.enddateFrom !=null && value.enddateTo===null){
              this.msgs.push({ severity: 'error', summary: '', detail: "End Date fields should not be empty" });
          }
          else{
             this.service.dayticketdetails(value)
              .subscribe(
                      res=>{
                              this.dayticket = res; 
                      },
                      error => {
                           return false;
                      }
              );
         }
   }
}

