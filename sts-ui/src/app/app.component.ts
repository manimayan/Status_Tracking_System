import { Component,OnInit } from '@angular/core';
import { NavbarService } from "app/navbar.service";
import { Intime } from "app/properties.service";
import { Employee } from "app/model/employee";
import { Subscription } from "rxjs/Subscription";
import { config } from "app/nav";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[NavbarService,Intime],
})
export class AppComponent {
    notReportTime: any;
resetpassword: string;
contact: string;
resetpasswordhome: string;
resetquestion: string;
    subscription: Subscription;
    employeedetails: Employee;
    buttondisabled: boolean;
    logout: string;
    changePassword: string;
    welcome: string;
    outTime: string;
    inTime: string;
    timing: string;
    ticketsHistoryStatus: string;
    clarification: string;
    notreportedEmployee: string;
    nochangeinStatus: string;
    timeReport: string;
    vacation: string;
    addVacation: string;
    plannedVacationReport: string;
    blockVacation: string;
    unplannedVacationReport: string;
    ticketoftheDay: string;
    ticketStatus: string;
    reportSummary: string;
    dailyStatusReport: string;
    report: string;
    addResource: string;
    resource: string;
    editPriority: string;
    releaseTickets: string;
    reassignTickets: string;
    assignTickets: string;
    allClosedTickets: string;
    myclosedTickets: string;
    deleteTickets: string;
    addtickets: string;
    ticket: string;
    uploadfile: string;
    modifyResource: string;
    home: string;


  constructor( public nav: NavbarService, public time: Intime) 
  { this.subscription=this.nav.get_empdetails().subscribe(
         value => {this.employeedetails = value; })}
              
             
  title = 'app';
  
  ngOnInit() {
      this.Intime();
      
      this. home = config.home;
      
      this.uploadfile =config.uploadFile;
      
      this. ticket           = config.ticket;
      this.addtickets        =config.addTickets;
      this. deleteTickets    =config.deleteTickets;
      this.myclosedTickets   =config.myclosedTickets;
      this. allClosedTickets =config.allClosedTickets;
      this.assignTickets     =config.assignTickets;
      this. reassignTickets  =config.reassignTickets;
      this.releaseTickets    =config.releaseTickets;
      this. editPriority     =config.editPriority;
      
      this.resource          =config.resource;
      this. addResource      = config.addResource;
      this.modifyResource    =config.modifyResource;
      
      this. report           =config.report;
      this.dailyStatusReport =config.dailyStatusReport;
      this. reportSummary    =config.reportSummary;
      this.ticketStatus      =config.ticketStatus;
      this.ticketoftheDay    =config.ticketoftheDay;
      this. ticketsHistoryStatus = config.ticketsHistoryStatus;
      this.clarification     =config.clarification;
      this.notreportedEmployee=config.notreportedEmployee;
      this. nochangeinStatus  = config.nochangeinStatus;
      this.timeReport         =config.timeReport;
      this.notReportTime      =config.notReportedTime;
      
      this.vacation           =config.vacation;
      this. addVacation       = config.addVacation;
      this.plannedVacationReport=config.plannedVacationReport;
      this. unplannedVacationReport = config.unplannedVacationReport;
      this.blockVacation      =config.blockVacation;
      this. timing            = config.timing;
      
      this.inTime             =config.inTime;
      this.outTime            =config.outTime;
      
      this. welcome           = config.welcome;
      this.changePassword     =config.changePassword;
      this.resetpassword     =config.resetpassword;
      this.resetpasswordhome     =config.resetpasswordhome;
      this.resetquestion     =config.resetquestion;
      this.contact     =config.contact;
      this.logout             =config.logout;
 }
  
  
 Username():string 
  {
   let username= this.nav.empdetails.getValue().employeeName;
 
   return  username;
   
 
  }
  Intime() 
  {
      
      var currentTime = this.time.date.getHours();
      
      var intime = this.time.inTimeHours;
      
      console.log("currentTime " + currentTime +" "+ "intime "+ intime)
      if ( intime <= currentTime) { 
          this.buttondisabled = false; 
      }
      else {
          this.buttondisabled =true ; 
      }
  } 
}
