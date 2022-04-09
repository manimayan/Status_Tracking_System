import { Component, OnInit } from '@angular/core';
import { NavbarService } from "app/navbar.service";
/*import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';*/
import { ReassignticketService } from './reassignticket.service';
import { Reassignticket } from 'app/model/reassignticket';
import { Employee } from 'app/model/employee';
import {SelectItem} from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';
import { MessageModule } from "primeng/primeng";
import { Ticket } from "app/model/ticket";
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-reassignticket',
  templateUrl: './reassignticket.component.html',
  styleUrls: ['./reassignticket.component.css'],
  providers :[ ReassignticketService ],
})
export class ReassignticketComponent{
subscription: Subscription;
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
value: any;
private reassignList: Reassignticket[] = [];
private employeeId: Employee[] = [];
public msgs: MessageModule[] = [];
private id: any[] = [];
private ticketid: any[] = [];
private reassignTicket: any[] = [];

public result:any[]=[];

  constructor(private service:ReassignticketService,public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
    this.getReassignDetails();
    this.getdetails();
    this.nav.show();
    this.getdumpdetails();
   
  }

 getReassignDetails() {
     
      this.service.getReassignDetails()
      .subscribe(
      res => {
          this.reassignList = res;
      });
  }
 
 getdetails(){
     this.service.getEmployeeDetails()
     .subscribe(
             res => {
                 this.employeeId=res;
                 this.result.push({'label' : 'Select a Employee Name','value': null}); 
                 this.employeeId.forEach(mod => {
                     this.result.push({'label' : mod. employeeName,'value': mod.employeeId});     
                 })
             });
     
 }
 

 getdumpdetails()
 {
     this.createdBy= this.nav.Dumpdetails.getValue().createdBy
     this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
 }
 
 ReassignUpdate(value)
 { this.msgs = [];
             this.service.ReassignUpdate(value.e_name,value.ticketId).subscribe(
                 res => {
                     this.getReassignDetails();
                     this.reassignTicket = res;
                     this.msgs.push({ severity: 'success', summary: '', detail: "Ticket Reassigned sucessfully!" });
                 },
             error => {
                 return false;
   });                   
                
 }

}