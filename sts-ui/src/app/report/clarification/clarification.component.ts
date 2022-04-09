import { Component, OnInit } from '@angular/core';

import { Ticket } from "app/model/Ticket";
import { Message } from "primeng/components/common/message";
import { DatePipe } from '@angular/common';  
import { clarificationservice } from "app/report/clarification/clarification.serviec";
import { Clarify } from "app/model/clarify";
import { Clarification } from "app/model/Clarification";
import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";


@Component({
  selector: 'app-clarification',
  templateUrl: './clarification.component.html',
  styleUrls: ['./clarification.component.css'],
  providers:[ clarificationservice,DatePipe]
})
export class ClarificationComponent implements OnInit {
    public  showReport: boolean;
    subscription: Subscription;
    public updated : boolean = true; 
    clarificationDescription: any;
    public msgs: any = ''; 
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
    private clarifyList: Clarify[] = [];
    private clari: Clarify[] = [];
private ticketid: any[] = [];
private result1: any[]=[];
public value:Clarification={
        clarificationId:null,
        clarificationDescription:null,
        employeeResponse:null,
        flag:null,
        date:null,
        ticket:{ticketId:null}
};

  constructor(private service:clarificationservice,private datePipe: DatePipe,public nav: NavbarService) { 
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.nav.show();
      this.clarifydisplay();
      this.geticketdetails();
      this.getdumpdetails();
      this.showReport=false;
  }
  
  clarificationDesc(event){

      if (!this.clarificationDescription)
          {
          this.updated=true;
          }
      else{
          this.updated=false;
      }
      
      
    } 
  
  Onclick(event){
      this.showReport=true;
  }
  
  geticketdetails()
  {
      console.log("in dispalying ticket details");
      this.service.getticketDetails()
      .subscribe(res => {
          this.ticketid=res;
          console.log(res);
          this.result1.push({'label' : '','value': null});
          this.ticketid.forEach(mod => {
              this.result1.push({'value' : mod.ticketId,'label':mod.ticketId+"-"+mod.ticketDescription});
                      
                  })
                  
                  console.log(this.result1)
              });
  }
  
  clarifydisplay()
  {
      
      this.service.clarifydisplay()
      .subscribe(
      res => {
          this.clarifyList = res;
          console.log(this.clarifyList);
    
      });
  }
  
  clarfication(value:Ticket){
      this.msgs = [];
         this.service.clarfication(value)
      .subscribe(
              res => {
                 
                  this.clari = res;
                  console.log("in return for search ticket"); 
                  console.log( "ticket check"+this.clari);
                  if( this.clari==null)
                      {
                      this.msgs.push({ severity:'error', summary:'', detail:"Enter Ticket Id!" });              
                      return true;
                      }
                  this.clari = res;
                  return true;
                  
            
              },
              error => {
                  console.log("err")
                  this.msgs.push({ severity:'error', summary:'', detail:"Invalid Ticket Id!" });
              });
  }

  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }

  update(data1,data2)
  {
    console.log("clariiiii"+data2);
      this.value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd');
      this.value.ticket.ticketId=data1;
      this.value.clarificationDescription=data2;
      this.value.flag=0;
       this.service.saveComment(this.value)
     .subscribe(
              data=>{
                      this.clarifydisplay();
                      this.clari=null;
                
              },
             
              error => {
                  console.log("err")
                  return false;
              });
          
  
}
  
  delete(value:any,value1:any){
      this.service.deletemethod(value,value1)
      .subscribe(
              data=>{
                      this.clarifydisplay();
              },
             
              error => {
                  console.log("err")
                  return false;
              });
  }
 

  
}


