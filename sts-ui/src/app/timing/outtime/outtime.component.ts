import { Component, OnInit } from '@angular/core';
import { OuttimeService } from "app/timing/outtime/outtime.service";
import { Timing } from "app/model/timing";
import {DropdownModule} from 'primeng/primeng';
import {Dropdown} from 'primeng/primeng'; 
import { Message } from 'primeng/primeng';
import { DatePipe } from '@angular/common';
import {SelectItem} from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";

@Component({
  selector: 'app-outtime',
  templateUrl: './outtime.component.html',
  styleUrls: ['./outtime.component.css'],
  providers:[OuttimeService,DatePipe]
})
export class OuttimeComponent implements OnInit {
    subscription: Subscription;
    employeedetails: Employee;
    private time:string;
    private outtimeList: Timing[] = [];
public msgs: Message[] = [];
public otime:any;
public value:Timing={
        timeId:null,
        employee: {employeeId:null},
        outTime: null,
        inTime: null,
        date: null,
        difference:null,
};
  constructor(private service:OuttimeService,private datePipe: DatePipe,public nav:NavbarService ) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
  }

  ngOnInit() {
    
      this.nav.navigationbar();
      this.getOuttimeDetails(this.value);    
      
  }
 getOuttimeDetails(value:Timing) { 
     this.msgs = [];
    value.timeId="0";
     value.employee.employeeId=this.nav.empdetails.getValue().employeeId;
     value.outTime=new Date();
   value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd');  
   this.otime=this.datePipe.transform(new Date(),'HH:mm:ss');
      this.service.getOuttimeDetails(value).subscribe(
              response=>{
                      this.msgs.push({ severity: 'success', summary: '', detail: "Out Time is  Added Sucessfully at"+this.otime  });
                    
              },
              error => {
                  return false;
              }
      );
  }
}
