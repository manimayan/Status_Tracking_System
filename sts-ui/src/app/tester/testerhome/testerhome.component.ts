import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarService } from "app/navbar.service";
import { TesterhomeService } from "app/tester/testerhome/testerhome.service";
import { Homedoc } from "app/model/Homedoc";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-testerhome',
  templateUrl: './testerhome.component.html',
  styleUrls: ['./testerhome.component.css'],
  providers:[TesterhomeService]
})
export class TesterhomeComponent implements OnInit {
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    employeedetails: Employee;
    subscription: Subscription;
    homeList: Homedoc[] = [];

  constructor(private service:TesterhomeService,public nav: NavbarService,public router:Router) { 
      
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
      this.getHomeDetails();
      this.getdumpdetails();
  }
  
  getHomeDetails() {
      this.service.getHomeDetails(this.nav.empdetails.getValue().employeeName)
          .subscribe(
          res => {
             
              this.homeList = res;
             
          });
  }
  
  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  
  updatecomment(data){

      this.router.navigate(["/tester/updatetester"], { queryParams: {ticketId: data.ticketId,ticketType:data.ticketType, ticketDescription:data.ticketDescription,applicationName:data.applicationName,priority:data.priority,activity:data.activity,status:data.status,testComment:data.testComment},}); 
      
      
  }

}
