import { Component, OnInit } from '@angular/core';
import { Security } from "app/model/security";
import { Message } from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { SecuritycheckService } from "app/employee/securitycheck/securitycheck.service";
import { Router, ActivatedRoute } from '@angular/router';
import getUniqueRandomArray from "get-unique-random-array";
import { SecurityComponent } from "app/employee/security/security.component";
import { SecurityService } from "app/employee/security/security.service";
import { Question } from "app/model/question";
import { Observable } from "rxjs/Observable";
import { UseremployeeService } from "app/employee/useremployee/useremployee.service";

@Component({

  selector: 'app-useremployee',
  templateUrl: './useremployee.component.html',
  styleUrls: ['./useremployee.component.css'],
providers:[UseremployeeService]
})

export class UseremployeeComponent implements OnInit {
public msgs: Message[] = [];
employeedetails: Employee;
subscription: Subscription;

public updated : boolean = true; 
supervisorName: any;
private supervisordetails:any[] = [];
private supervisorlist:any[]=[];

  constructor(private service:UseremployeeService, private router: Router,public nav: NavbarService,) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => {
                       this.employeedetails = value;
                       });
}

  ngOnInit() {
      this.getsupervisordetails();    
 }
  
  addemployee(value:Employee) {
      this.msgs = [];
      this.service.addemployee(value).subscribe(
             
      response=>{
                 this.msgs.push({ severity: 'success', summary: '', detail: "Resource Added Sucessfully! Your temporary password is pass " });
      },
      error => {
          this.msgs.push({ severity: 'error', summary: '', detail: "Enter a valid Fields" });
          return false;
      }); 
      }
  getsupervisordetails()
  {
      this.service.getsupervisordetails().
      subscribe(res => {
          console.log(res);
          this.supervisordetails=res;
          this.supervisorlist.push({'label' : '','value': null});
          this.supervisordetails.forEach(mod => {
              this.supervisorlist.push({'value' : mod,'label':mod});     
                  })
              });
  }
  
  supervisorname(event){

      if (!this.supervisorName)
          {
          this.updated=true;
          }
      else{
          this.updated=false;
      }
  }

}