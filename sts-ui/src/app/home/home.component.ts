import { Component, OnInit } from '@angular/core';
import { HomeService } from "app/home/home.service";
import { NavbarService } from "app/navbar.service";
import { Homeadmin } from "app/model/homeadmin";
import { Clarify } from "app/model/clarify";
import { Router } from '@angular/router';
import { Homedoc } from "app/model/Homedoc";
import { MessageModule } from "primeng/primeng";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { assignticketService } from "app/ticket/assignticket/assignticket.service";
import { UploadTicket } from "app/model/UploadTicket";


@Component({
  selector: 'app-report',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers:[HomeService,assignticketService]
})
export class HomeComponent implements OnInit {

    public createdBy : string;
    public createdOn : Date;
    Dumpdetails: UploadTicket;
    employeedetails: Employee;
    private ticketDumps: UploadTicket[] = [];
    public updated : boolean = true; 
    homeupdate: any;

subscription: Subscription;
    private homeList: Homeadmin[] = [];
private clarifyList: Clarify[] = [];
private clari: Clarify[] = [];
private homedocList: Homedoc[] = [];
public msgs: MessageModule[] = [];
  constructor(private service:HomeService,public nav: NavbarService,public router:Router) {
      
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }




  ngOnInit(){
      this.nav.navigationbar();

      this.clarifydisplay();
      this.getHomeDetails();
      this. getdocdetails();
      this.getTicketDump();

  }
 
  clarifydisplay()
  {
      this.service.clarifydisplay(this.nav.empdetails.getValue().employeeId).subscribe(
      res => {
          console.log(res);
          this.clarifyList = res;
          if( this.clarifyList[0]==null){
              this.nav.hideclarify();
           }
          else{
              this.nav.showclarify();
          }
      
          console.log("clarify list"+this.clarifyList);
          console.log(this.clarifyList);
    
      });
  } 
  
  getTicketDump(){
      this.service.getTicketDump().subscribe(
              res => {
                  res.forEach(item=>
                  {
                     item.createdOn.length = 3;
                     for(let i=1;i<=item.createdOn.length; i++)
                         {
                         var createdOn = [];
                         var length = createdOn.push(item.createdOn); 
                         }
                     item.createdOn  = item.createdOn.reverse();
                     item.createdOn = item.createdOn.join('/');
                     this.createdOn=  item.createdOn;
                     this.createdBy=  item.createdBy;
                  })
                     this.ticketDumps = res; 
                     console.log(this.ticketDumps);
                  this.nav.setDumpdetails(this.ticketDumps[0]);
                  
   
  });
  }
  
  getHomeDetails() {
      this.service.getHomeDetails(this.nav.empdetails.getValue().employeeId)
          .subscribe(
          res => {
             
              this.homeList = res;
             
          });
  }
  getdocdetails() {
      this.service.getClosedDetails(this.nav.empdetails.getValue().employeeId)
          .subscribe(
          res => {
            
              this.homedocList = res;
              console.log(res);
              this.msgs.push({ severity: 'success', summary: '', detail: "Sucessfully updated!" });
              
          },
          error => {
              return false;
             
          });
  }
  
  
  
  updateresponse(value:any,value1:any,value2){
 
      this.service.updateresponse(value,value1,value2)
      .subscribe(
              data=>{
                      this.clarifydisplay();
              },
             
              error => {
                  console.log("err")
                  return false;
              });
  }

  
  updatecomment(data){

      this.router.navigate(["/report/updatestatus"], { queryParams: {  ticketId: data.ticketId,ticketType:data.ticketType, ticketDescription:data.ticketDescription,applicationName:data.applicationName,priority:data.priority,activity:data.activity,status:data.status,devComment:data.devComment,tester:data.tester},}); 
      
      
  }
  
  update(ticketId,remedy,documentationDescription,documentationComment){
      console.log(ticketId.ticketId);
    console.log(remedy.remedy);

      this.router.navigate(["/report/document"], { queryParams: { ticketId: ticketId.ticketId,remedy:remedy.remedy,documentationDescription:documentationDescription.documentationDescription,documentationComment:documentationComment.documentationComment},}); 
      
  }
}



