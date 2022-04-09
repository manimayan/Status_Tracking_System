    import { Component, OnInit } from '@angular/core';

import { assignticketService } from './assignticket.service';
import { UploadTicket } from "app/model/UploadTicket";
import { Employee } from "app/model/employee";
import { DropdownModule } from "primeng/components/dropdown/dropdown";
import { Ticket } from "app/model/ticket";
import { MessageModule } from "primeng/primeng";
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
@Component({
  selector: 'app-assignticket',
  templateUrl: './assignticket.component.html',
  styleUrls: ['./assignticket.component.css'],
  providers:[assignticketService]
})
export class AssignticketComponent implements OnInit {

    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    subscription: Subscription; 
public msgs: MessageModule[] = [];

private employeeId: Employee[] = [];
public result:any[]=[]; 
private ticketList: UploadTicket[] = [];
private ticketDumps: UploadTicket[] = [];
public assignTicket:any[]=[]; 

constructor(private service:assignticketService,public nav: NavbarService) { 
    this.subscription=this.nav.getDumpdetails().subscribe(
            value => { 
                     this.Dumpdetails = value; 
                     });


}

  ngOnInit() {
      this.nav.show();
      this.getTicketDetails();
      this.getEmployeeName();
      this.getdumpdetails();
      
  }

getTicketDetails() {
      this.service.getTicketDetails().subscribe(
           res => {
                  this.ticketList = res;
          });
  }

convert(str) {
    var date = new Date(str),
        mnth = ("0" + (date.getMonth()+1)).slice(-2),
        day  = ("0" + date.getDate()).slice(-2);
    return [  date.getFullYear(),mnth, day ].join("-");
    }



getdumpdetails()
{
    this.createdBy= this.nav.Dumpdetails.getValue().createdBy
    this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
}
      
getEmployeeName() {
          this.service.getEmployeeDetails().subscribe(
            res => {
                   this.employeeId=res;
       
                   this.employeeId.forEach(mod => {
                   this.result.push({'label' : mod. employeeName,'value': mod.employeeId});
                  })                       
                  });
          }


AssignUpdate(value)
{     
    this.msgs = [];
       this.service.AssignUpdate(value.employeeId,value.ticketId,value.applicationName).subscribe(
                res => { 
                    this.getTicketDetails();
                    this.assignTicket = res;
                    this.msgs.push({ severity: 'success', summary: '', detail: "Ticket Assigned sucessfully!" });
                       },
        error => {
                 return false;
         }); 
} 
}