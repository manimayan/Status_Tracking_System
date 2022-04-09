import { Component, OnInit } from '@angular/core';
import { Security } from "app/model/security";
import { Message } from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { Router, ActivatedRoute } from '@angular/router';
import getUniqueRandomArray from "get-unique-random-array";
import { SecurityComponent } from "app/employee/security/security.component";
import { SecurityService } from "app/employee/security/security.service";
import { Question } from "app/model/question";
import { Observable } from "rxjs/Observable";
import { Questioncheck } from "app/model/questioncheck";
import { ResetquestionService } from "app/employee/resetquestion/resetquestion.service";

@Component({

  selector: 'app-resetquestion',
  templateUrl: './resetquestion.component.html',
  styleUrls: ['./resetquestion.component.css'],
providers:[ResetquestionService]
})

export class ResetquestionComponent implements OnInit {
    employeeId: any;
public msgs: Message[] = [];
employeedetails: Employee;
subscription: Subscription;

private empid: any[] = [];
private result1: any[]=[];

public value:Employee={
        employeeId:null,
        employeeName:null,
        designation: null,
        skills:null,
        supervisorName : null,
        isAdmin: null,
        isSupervisor: null,
       // emailId: null,
        role:null,
        isActive:null,
        isInclude: null,
        password:null,
        nickName:null,
        report:null,
        newpassword:null,
        reenterpassword:null,
       // contactNumber:null,
        flag:null, 
};

  constructor(private service:ResetquestionService, private router: Router,public nav: NavbarService) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => {
                       this.employeedetails = value;
                       });
}

  ngOnInit() {
      this.getemployeedetails();
 }
  
  getemployeedetails()
  {
      this.service.getemployeeDetails()
      .subscribe(res => 
      {
         
          this.empid=res;
          this.result1.push({'label' : '','value': null});
          this.empid.forEach(mod => {
              this.result1.push({'value' : mod.employeeId,'label':mod.employeeId+"-"+mod.employeeName});
                  })
              });
  }
  
  deleteemployee(value:Questioncheck)
  {
      this.msgs = []; 
      this.service.employeeDelete(this.employeeId).subscribe(
              res => {
                  this.msgs.push({ severity: 'success', summary: '', detail: " Security Questions has been successfully Deleted!!! " });
              })
  }

}