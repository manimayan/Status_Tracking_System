import { Component, OnInit } from '@angular/core';

import { DatePipe } from '@angular/common'; 
import { Updatestatus } from "app/model/updatestatus";
import { SelectItem, Message } from "primeng/primeng";
import { ActivatedRoute, Router } from '@angular/router';
import { Application } from "app/model/application";
import { Employee } from "app/model/employee";
import { UpdatestatusService } from "app/report/updatestatus/updatestatus.service";
import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";
@Component({
  selector: 'app-homeadmin',
  templateUrl: './updatestatus.component.html',
  styleUrls: ['./updatestatus.component.css'],
  providers:[UpdatestatusService,DatePipe]


})


export class UpdatestatusComponent implements OnInit {
    subscription: Subscription;
    Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
    public msgs: Message[] = [];
    temp: number;
    prioritycheck: any;
    activeEmployee: any;
    selectedTicketId: any;
    chosepriority: any;
    cmt: any;
private updatestatus: Updatestatus[] = [];
i: Number;
   
   public appName: any;
  public testerName: any;
    public applicationName: Application[];
public tester:Employee[];
public result:any[]=[]; 
public No:any[]=[]; 
public Yes:any[]=[]; 
public display:any[]=[]; 
private statusList: Updatestatus[] = [];
//public value:Updatestatus;
type: SelectItem[];
prio: SelectItem[];
act: SelectItem[];
stat: SelectItem[];
show = false;

public ticketType:any;
public ticketId:any;
public ticketDescription:any;
//public applicationName1:any;
public priority:any;
public activity:any;
public status:any;
public devComment:any;


  constructor(private service:UpdatestatusService,private router: Router,private datePipe: DatePipe,private activatedRoute:ActivatedRoute,public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
      
      this.type = [
                      {label: 'Project', value: 'Project'},
                      {label: 'Support', value: 'Support'},
                      {label: 'Enhancement', value: 'Enhancement'},
                  ];
      this.prio = [
                      { label: '1', value: '1' },
                      { label: '2', value: '2' }, 
                      { label: '3', value: '3' },
                      { label: '4', value: '4' },
                      { label: '5', value: '5' },
                      { label: '6', value: '6' },
                      { label: '7', value: '7' },
                      { label: '8', value: '8' },
                      { label: '9', value: '9' },
                      { label: '10',value: '10'}
                  ];
      this.act =  [   
                      {label: 'Estimation', value: 'Estimation'},
                      {label: 'Analysis', value: 'Analysis'},
                      {label: 'Design', value: 'Design'},
                      {label: 'Build', value: 'Build'},
                      {label: 'Deployment', value: 'Deployment'},
                      {label: 'Documentation', value: 'Documentation'},                     
                      {label: 'Testing', value: 'Testing'},
                      {label: 'UAT', value: 'UAT'}
                  ];
    this.stat = [
                      {label: 'In Progress', value: 'In Progress'},
                      {label: 'On Hold', value: 'On Hold'},
                      {label: 'Approved', value: 'Approved'},
                      {label: 'Completed', value: 'Completed'},
                      {label: 'Closed', value: 'Closed'},
                      {label: 'Not Started', value: 'Not Started'}
                  ];
  }
  
  

  ngOnInit() {
      
      this.getdumpdetails();
      this.activatedRoute.queryParams.subscribe(params => {
          let iid = params['ticketId'];
          this.ticketId=iid;
          let ttype = params['ticketType'];
          this.ticketType=ttype;
          let desc = params['ticketDescription'];
          this.ticketDescription=desc;
          let appname = params['applicationName'];
          this.applicationName=appname;
          
          let pri = params['priority'];
          this.priority=pri;
          let st = params['status'];
          this.status=st;
          let test = params['tester'];
          this.testerName=test;
         
          let actt = params['activity'];
          this.activity=actt;
          let cmt = params['devComment'];
          this.devComment=cmt;
          
                 }); 
      this.nav.show();
      this.getdumpdetails();
      this.getTestename();
     // this.getdetails(this.value);
      
  }
  getAppdetails(){
      console.log("in dispalying applicationName name");
   /*   this.result.push({ 'label': 'All', 'value': '0' });*/
      this.service.getAppdetails()
      .subscribe(
              res => {
                  this.applicationName=res;
                  this.result.push({'value' : null ,'label':''})
                  this.applicationName.forEach(mod => {
                      this.result.push({'value' : mod.applicationId,'label':mod.applicationName});
                        })
                  
                  console.log("ckeck" + this.result)
              });
     
  }
 
  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  
  getTestename(){
      console.log("in dispalying tester name");
   /*   this.result.push({ 'label': 'All', 'value': '0' });*/
      this.service.getTestename()
      .subscribe(
              res => {
                  this.tester=res;
                  console.log("hai:" + res);
                  this.display.push({'value' : null ,'label':''})
                  this.tester.forEach(mod => {
                      
                      this.display.push({'value' : mod.employeeName,'label':mod.employeeName});     
                  })
                  
                  console.log("heloo:" + this.display)
              });    
  }
 /* getdetails(value:Updatestatus)
  {
      console.log("sagbcfkjds");
      console.log(value);
      this.service.getdetails(value)
      .subscribe(
      res => {
          this.statusList = res;
          console.log("here:");
          console.log(this.statusList);
      });
}  */
  fClear(){
      this.activatedRoute.queryParams.subscribe(params => {
          
          let ttype = params['ticketType'];
          this.ticketType=ttype;
          let pri = params['priority'];
          this.priority=pri;
          let st = params['status'];
          this.status=st;
          let test = params['tester'];
          this.testerName=test;
          let actt = params['activity'];
          this.activity=actt;
          let cmt = params['devComment'];
          this.devComment=cmt;
         
      if( (this.act==null))
  
  {
          this.ticketType = null;
          this. priority= null;
          this. activity= null;
          this. status= null;
          this.testerName= null;
          this.devComment= null;
          this.No= null;
          this.Yes= null;
      
  }
      else{
          this.devComment= null;
          this.No= null;
          this.Yes= null;
   }
      });
 }
  
 /* saveticketdetails(value:Updatestatus)
  {
      console.log("sagbcfkjds");
      console.log(value.testerName);
      console.log(value.applicationName);
      value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd'); 
      console.log(value);
      console.log(value.workedOnToday)
      if(value.workedOnToday===null){
          
      }
      
    this.service.saveticketdetails(value)
      .subscribe(
      res => {
          this.statusList = res;
          this.router.navigateByUrl('/home');
          console.log("here:");
          console.log(this.statusList);
         // this.router.navigateByUrl('/home');
      });
  }*/
  
  saveticketdetails(value:Updatestatus)
  {
   this.msgs=[];
      this.temp=0;
      value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd'); 
     this.chosepriority=value.priority
     this.selectedTicketId=value.ticketId
     this.activeEmployee=this.nav.empdetails.getValue().employeeId; 
      this.prioritycheck=this.service.checkpriority(this.selectedTicketId,this.activeEmployee).subscribe(
              res => { 
                  
                       this.updatestatus = res; 
                       for ( let entry of this.updatestatus ) {
                           if(  entry== this.chosepriority){
                               this.temp=1;
                               this.msgs.push({ severity: 'error', summary: '', detail: "Priority already exist" });
                               return false;
                           }
                       }
                         if(this.temp ==0){
                       this.service.saveticketdetails(value)
                       .subscribe(
                       val => {
                               this.statusList = val;
                             this.router.navigateByUrl('/home');
                       
                       });
                         }
                 
                       });
      

  }
}
