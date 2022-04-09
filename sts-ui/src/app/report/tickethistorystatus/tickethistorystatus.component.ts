import { Component, OnInit } from '@angular/core';
import {SelectItem} from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';

import { Ticket } from "app/model/ticket";
import { Tickethistory } from "app/model/tickethistory";
import { Employee } from "app/model/employee";
import { Tickethistoryservice } from "app/report/tickethistorystatus/tickethistorystatus.service";
import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";


@Component({
  selector: 'app-tickethistorystatus',
  templateUrl: './tickethistorystatus.component.html',
  styleUrls: ['./tickethistorystatus.component.css'],providers:[Tickethistoryservice]

})
export class TickethistorystatusComponent implements OnInit {
       subscription: Subscription;
      Dumpdetails: UploadTicket;
      public createdBy : string;
      public createdOn : Date;
public ticketId:any;
public employeename:any;
public days:any;
public showReport : boolean;

public tickethistory:Tickethistory[]=[];
 msgs: any;
 public ename:any[]=[];
 public result:any[]=[];
public tickId:any[]=[];
public result1:any[]=[];
private clari :Tickethistory[]=[];

  constructor(private service:Tickethistoryservice,public nav: NavbarService ) { 
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }
  ngOnInit() {  
      this.nav.show();
      this.getdetails();
      this.getdumpdetails();
      this.gettickdetails();
      this.showReport=false;
     
      
      
  }
  
  gettickdetails(){
      this.service.getticketDetails()
      .subscribe(
              res => {
                  this.tickId=res;
                  this.result1.push({'label' : '','value': null});
                  this.tickId.forEach(mod => {
                      this.result1.push({'value' : mod.ticketId,'label':mod.ticketId+"-"+mod.ticketDescription});
                      
                  })
                  
                 
              });
      
  }
    
  Onclick(event){
      this.showReport=true;
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
                  this.ename=res;
                  this.result.push({'label' : '','value': null});
                  this.ename.forEach(mod => {
                      this.result.push({'value' : mod,'label':mod});
                      
                  })
                  
                 
              });
      
  }
  /*change(addday) {
      var date = new Date(addday),
          mnth = ("0" + (date.getMonth()+1)).slice(-2),
          day  = ("0" + (date.getDate()+1)).slice(-2);
      return [  date.getFullYear(),mnth,day ].join("-");
      }  */
  

submit(value1:any,value2:any,value3:any)
{
      /*  this.days=value.days;
        this.employeename=value.employeename;
        this.ticketId=value.ticketId;
       */
        console.log(value1)
        console.log(value1+value2+value3);
        
        this.service.submit(this.tickId,this.ename,this.days).subscribe(
                res => {
                   /* this.clari=res
                    if( this.clari==null)
                    {
                    this.msgs.push({ severity:'error', summary:'', detail:"Invalid Ticket Id!" });
                    return true;
                    }
                  
                    res.forEach(item=>{
                        var date = this.change(item.date);
                        item.date=date;
                    })*/
                    console.log(res);
                    this.tickethistory = res;
                });
}
clear()

{
    }
}

