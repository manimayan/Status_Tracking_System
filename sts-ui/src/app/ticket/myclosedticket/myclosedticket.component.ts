import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Closed } from "app/model/closed";
import { MyclosedService } from "app/ticket/myclosedticket/myclosedticket.service";
import { NavbarService } from "app/navbar.service";
import { Employee } from "app/model/employee";
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'myclosedticket',
  templateUrl: './myclosedticket.component.html',
  styleUrls: ['./myclosedticket.component.css'],
  providers:[MyclosedService]
})
export class MyclosedticketComponent implements OnInit {
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    subscription: Subscription;
    employeedetails: Employee;
    private closedList: Closed[] = [];
  constructor(private service:MyclosedService,public nav: NavbarService) { 
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
      this.getClosedDetails();
      this.getdumpdetails();
  }
 

  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  
  getClosedDetails() {
      this.service.getClosedDetails(this.nav.empdetails.getValue().employeeId)
          .subscribe(
          res => {
              console.log(res);
               this.closedList = res;
             
          });
  }
}
