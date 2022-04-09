import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';

import { Ticket } from "app/model/ticket";
import { Message } from "primeng/primeng";
import { NavbarService } from 'app/navbar.service';
import { AddticketService } from "app/ticket/addticket/addticket.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-addticket',
  templateUrl: './addticket.component.html',
  styleUrls: ['./addticket.component.css'],
 providers:[AddticketService]
})
export class AddticketComponent implements OnInit {
    Dumpdetails: UploadTicket;
    employeedetails: Employee;
    subscription: Subscription;
public createdBy : string;
public createdOn : Date;
public appId: any;
public app:any[]=[];
public msgs: Message[] = [];
public appData:any[]=[];
public id:any="app";



  constructor(private service:AddticketService,public nav: NavbarService) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.nav.navigationbar();
      this.getApplicationname();
      this.getdumpdetails();
  }
  
  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }

  getApplicationname()
  {
      this.service.getAppname()
      .subscribe(
              res => {
                  
                  this.appData = res;
                  this.app.push({'label' : '','value': null});
                  this.appData.forEach(mod => {      
                  this.app.push({'label' : mod.applicationName,'value': mod.applicationName+"-"+mod.applicationId });
                  })
              });
  }
  
  
   saveAddticket(value:Ticket,appId:any){
       this.msgs = [];
       if (value.appId.indexOf('-') > -1) {
           var toArray =  this.appId.split("-");
           this.id=toArray[1];
           appId=this.id.trim();
       }
      
     this.service.saveAddticket(value,appId,this.nav.empdetails.getValue().employeeId).subscribe(
          response=>{
                  this.msgs.push({ severity: 'success', summary: '', detail: "Ticket Added Sucessfully!" });
                  return true; 
          },
          error => {
              this.msgs.push({ severity: 'error', summary: '', detail: "TicketId already exist!" });
              return false;
          });

}
   clear()
   {
       
   }
}

