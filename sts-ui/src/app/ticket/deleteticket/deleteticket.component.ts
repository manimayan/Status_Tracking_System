import { Component, OnInit } from '@angular/core';

import { Ticket } from "app/model/ticket";
import { DeleteticketService } from "app/ticket/deleteticket/deleteticket.service";
import { NavbarService } from "app/navbar.service";
import { DeleteTicket } from "app/model/DeleteTicket";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";


@Component({
  selector: 'app-deleteticket',
  templateUrl: './deleteticket.component.html',
  styleUrls: ['./deleteticket.component.css'],providers:[DeleteticketService]
})
export class DeleteticketComponent implements OnInit {
    subscription: Subscription;
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    msgs: any[];  
    value: any;
    ticketid: any;
    private deleted: DeleteTicket[] = [];

  constructor(private service:DeleteticketService,public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });



  }

  ngOnInit() {
      this.nav.show();
      this.getTicketDetails();
      this.getdumpdetails();
  }

  getTicketDetails() {
      this.service.getTicketDetails()
          .subscribe(
          res => {
              this.deleted = res;
          });
  }
  
  deleteTick(value)
  {
      this.msgs = []; 
          this.service.deleteTick(value.ticketId).subscribe(
                  res => {
                      this.getTicketDetails();
                  }); 
  }
  


  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }

}



