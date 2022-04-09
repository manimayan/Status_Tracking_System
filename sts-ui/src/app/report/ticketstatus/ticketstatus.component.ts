
import { Component, OnInit } from '@angular/core';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Ticketstatus } from "app/model/ticketstatus";
import { DropdownModule } from "primeng/components/dropdown/dropdown";  
import { TicketstatusService } from "app/report/ticketstatus/ticketstatus.service";
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-ticketstatus',
  templateUrl: './ticketstatus.component.html',
  styleUrls: ['./ticketstatus.component.css'],providers:[TicketstatusService]
})
export class TicketstatusComponent implements OnInit {
    subscription: Subscription;
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
    private ticketid: any[] = [];
    private result: any[]=[];
private result1: any[]=[];
    private employeeName: any[]=[];
    private empList: Ticketstatus[] = [];
    public showReport : boolean;

constructor(private service:TicketstatusService,public nav: NavbarService) {
    this.subscription=this.nav.getDumpdetails().subscribe(
            value => { 
                     this.Dumpdetails = value; 
                     });
}


  ngOnInit() {
      this.getdetails();
      this.geticketdetails();
      this.getdumpdetails();
      this.nav.show();
      this.showReport=false;
  }
  getTicketstatus(value1,value2) {
      console.log("value"+value1.value);
      this.service.getTicketstatus(value1.value,value2.value)
          .subscribe(
          res => {
              res.forEach(item=>{
                  var date = this.change(item.updated_on);
                  console.log(date);
                  item.updated_on=date;
                      
              })
              console.log(res);
              this.empList = res;
              console.log(this.empList);
          });
  }
  
  getdumpdetails()
  {
  this.createdBy= this.nav.Dumpdetails.getValue().createdBy
  this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  
  getdetails(){
      this.service.getEmployeeDetails()
      .subscribe(
              res => {
                  this.employeeName=res;
                  this.result.push({'label' : '','value': null});
                  this.employeeName.forEach(mod => {
                      this.result.push({'value' : mod.employeeId,'label':mod.employeeName});
                      
                  })
                  
                 
              });
      
  }
  
  Onclick(event){
      this.showReport=true;
  }
  
  geticketdetails()
  {
      this.service.getticketDetails()
      .subscribe(res => {
          this.ticketid=res;
          this.result1.push({'label' : '','value': null});
          this.ticketid.forEach(mod => {
              this.result1.push({'value' : mod.ticketId,'label':mod.ticketId+"-"+mod.ticketDescription});     
                  })
              });
  }
  
  
  change(addday) {
      var date = new Date(addday),
          mnth = ("0" + (date.getMonth()+1)).slice(-2),
          day  = ("0" + (date.getDate()+1)).slice(-2);
      return [  date.getFullYear(),mnth, day ].join("-");
      }  
}
