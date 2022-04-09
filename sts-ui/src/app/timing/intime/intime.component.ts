import { Component, OnInit } from '@angular/core';
import {DropdownModule} from 'primeng/primeng';
import {Dropdown} from 'primeng/primeng'; 
import { Message } from 'primeng/primeng'; 
import { Timing } from "app/model/timing";
import { DatePipe } from '@angular/common'; 
import { IntimeService } from "app/timing/intime/intime.service";
import {SelectItem} from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";

@Component({
  selector: 'app-intime',
  templateUrl: './intime.component.html',
  styleUrls: ['./intime.component.css'],
  providers:[IntimeService,DatePipe]
})
export class IntimeComponent implements OnInit {
    subscription: Subscription;
    employeedetails: Employee;

    //private time:string;
private intimeList: Timing[] = [];
public msgs: Message[] = [];
public itime:any;
public value:Timing={
        timeId:null,
        inTime: null,
        outTime:null,
        difference:null,
        date: null,
        employee:{employeeId: null},
       }
  constructor(private service:IntimeService,private datePipe: DatePipe,public nav:NavbarService) {
    
    this.subscription=this.nav.get_empdetails().subscribe(
            value => { 
                     this.employeedetails = value; 
                     });
}

  ngOnInit() {
      this.nav.navigationbar();
      this.getIntimeDetails(this.value);
      
     
  }
  getIntimeDetails(value:Timing) {
      this.msgs = [];
      value.employee.employeeId=this.nav.empdetails.getValue().employeeId;
      value.inTime=new Date();
      value.date= this.datePipe.transform(new Date(),'yyyy-MM-dd');
      this.itime=this.datePipe.transform(new Date(),'HH:mm:ss');
      this.service.getIntimeDetails(value).subscribe(
              response=>{
                      this.msgs.push({ severity: 'success', summary: '', detail: "In Time is  Added Sucessfully at"+" "+this.itime });
                    
              },
              error => {
                  return false;
              }
      );
  }
  
  
}
