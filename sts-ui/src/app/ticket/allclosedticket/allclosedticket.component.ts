
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Closed } from "app/model/closed";
import { MyclosedService } from "app/ticket/myclosedticket/myclosedticket.service";
import { NavbarService } from "app/navbar.service";
import { AllclosedService } from "app/ticket/allclosedticket/allclosedticket.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'allclosedticket',
  templateUrl: './allclosedticket.component.html',
  styleUrls: ['./allclosedticket.component.css'],
  providers:[AllclosedService]
})
export class AllclosedticketComponent implements OnInit {
    subscription: Subscription;
    private closedList: Closed[] = [];
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;

  constructor(private service:AllclosedService,public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.getdumpdetails();
      this.nav.show();
      this.getAllClosedDetails();
  }

  getAllClosedDetails() {

      this.service.getAllClosedDetails()
          .subscribe(
          res => {
          
              console.log(res);
              this.closedList = res;
             
          });
  }
  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
}
