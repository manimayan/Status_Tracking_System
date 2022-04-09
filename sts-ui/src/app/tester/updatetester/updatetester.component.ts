import { Component, OnInit } from '@angular/core';

import { DatePipe } from '@angular/common'; 
import { Updatestatus } from "app/model/updatestatus";
import { SelectItem } from "primeng/primeng";
import { ActivatedRoute, Router } from '@angular/router';
import { Application } from "app/model/application";
import { Employee } from "app/model/employee";
import { NavbarService } from "app/navbar.service";
import { UpdatetesterService } from "app/tester/updatetester/updatetester.service";
@Component({
  selector: 'app-homeadmin',
  templateUrl: './updatetester.component.html',
  styleUrls: ['./updatetester.component.css'],
  providers:[UpdatetesterService,DatePipe]


})


export class UpdatetesterComponent implements OnInit {
    cmt: any;
   
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
public testComment:any;


  constructor(private service:UpdatetesterService,private router: Router,private datePipe: DatePipe,private activatedRoute:ActivatedRoute,public nav: NavbarService) {
 
      this.act =  [     {label: 'Analysis', value: 'Analysis'},
                        {label: 'Build', value: 'Build'},
                        {label: 'Deployment', value: 'Deployment'},
                        {label: 'Design', value: 'Design'},
                        {label: 'Documentation', value: 'Documentation'},
                        {label: 'Estimation', value: 'Estimation'},
                        {label: 'Testing', value: 'Testing'},
                        {label: 'UAT', value: 'UAT'}
                  ];

    this.stat = [
                      {label: 'In Progress', value: 'In Progress'},
                      {label: 'On Hold', value: 'On Hold'},
                      {label: 'Approved', value: 'Approved'},
                      {label: 'Completed', value: 'Completed'},
                      {label: 'Not Started', value: 'Not Started'}
                  ];
  }
  
  
ngOnInit() {
      

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

          let actt = params['activity'];
          
          this.activity=actt;
          let st = params['status'];
          this.status=st;
          
         
          let cmt = params['testComment'];
          this.testComment=cmt;
                 }); 
     //this.getAppdetails();
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

  clear(){
      this.activatedRoute.queryParams.subscribe(params => {
          
          let ttype = params['ticketType'];
          this.ticketType=ttype;
          let pri = params['priority'];
          this.priority=pri;
          let st = params['status'];
          this.status=st;
          let actt = params['activity'];
          this.activity=actt;
          let cmt = params['testComment'];
          this.testComment=cmt;
         
      if( (this.act==null))
  
  {
          this.ticketType = null;
          this. priority= null;
          this. activity= null;
          this. status= null;
          this.testComment= null;
          this.No= null;
          this.Yes= null;
      
  }
      else{
          this.testComment= null;
          this.No= null;
          this.Yes= null;
   }
      });
 }

  
  savetesterdetails(value:Updatestatus)
  {
      
      console.log(value);
      console.log(value.testerName);
      console.log(value.applicationName);
      value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd'); 
      console.log(value);
      console.log(value.workedOnToday)/*
      if(value.workedOnToday===null){
          
      }*/
      
    this.service.savetesterdetails(value)
      .subscribe(
      res => {
          this.statusList = res;
          this.router.navigateByUrl('/tester/testerhome');
          console.log("tester here:");
          console.log("tester details"+this.statusList);
         // this.router.navigateByUrl('/home');
      });
  }
}


