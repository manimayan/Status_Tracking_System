import { Component, OnInit } from '@angular/core';

import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Dayticket } from "app/model/dayticket";
import { TicketofthedayService } from "app/report/ticketoftheday/ticketoftheday.service";
import { NavbarService } from 'app/navbar.service';
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-ticketoftheday',
  templateUrl: './ticketoftheday.component.html',
  styleUrls: ['./ticketoftheday.component.css'],
  providers:[TicketofthedayService]
})
export class TicketofthedayComponent implements OnInit {
    subscription: Subscription;
private dayticket: Dayticket[] = [];
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
  constructor( private service:TicketofthedayService,public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.dayticketdetails();
      this.nav.show();
      this.getdumpdetails();
  }

 
  dayticketdetails() {
      this.service.dayticketdetails()
          .subscribe(
          res => {
              console.log(res)
              this.dayticket = res;
          });
  }
  
  getdumpdetails()
  {
    this.createdBy= this.nav.Dumpdetails.getValue().createdBy
    this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
}
