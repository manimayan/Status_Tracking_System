import { Component, OnInit } from '@angular/core';
import {Vacation} from 'app/model/vacation';
import { FormControl, FormGroup, Validators,FormBuilder, FormArray } from '@angular/forms';
import {AddvacationService} from './addvacation.service'

import {MessageModule} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
import { Publicholidays } from "app/model/publicholidays";
import { NavbarService } from "app/navbar.service";
import { Employee } from "app/model/employee";
import { Subscription } from "rxjs/Subscription";
@Component({
  selector: 'app-addvacation',
  templateUrl: './addvacation.component.html',
  styleUrls: ['./addvacation.component.css'],
  providers:[ AddvacationService,MessageService],
})
export class AddvacationComponent implements OnInit {
    subscription: Subscription;
    employeedetails: Employee;

public msgs: MessageModule[] = [];
public vacationTypes =[{value:'Vacation',display:'Vacation'},
                       {value:'Optional',display:'Optional'},
                       {value:'Comp-off',display:'Comp-off'},
                       {value:'Sick',display:'Sick'}];
public comments : string = "";
public vacationDateFrom : any ="";
public vacationDateTo : any ="";
public vacationType : any = "";
public vacationComments : any = "";
public isVacation : boolean = true;
public multiple : Array<Date> =[];
invalidDates: Array<Date>; 
public addvacation:Vacation={
        vacationId:null,
        employeeName : null,
employee:{employeeId:null},
vacationDateFrom :null,
vacationDateTo : null,
vacationType : null,
vacationComments : null,
multiple :  null
        
}

constructor(private service:AddvacationService,private messageService: MessageService,public nav:NavbarService) { 
    this.subscription=this.nav.get_empdetails().subscribe(
            value => { 
                     this.employeedetails = value; 
                     });
}

ngOnInit() {
    this.nav.navigationbar();
    this.getdisabledDays();
    }
  onChange(event){
    
      if(!this.vacationDateFrom){
          this.isVacation =  true;
          return;
      }
      if(!this.vacationDateTo){
          this.isVacation =  true;
          return;
      }
      if(!this.vacationType){
          this.isVacation =  true;
          return;
      }
      if(!this.vacationComments){
          this.isVacation =  true;
          return;
      }
      this.isVacation = false;
      }
  
  convert(str) {
      var date = new Date(str),
          mnth = ("0" + (date.getMonth()+1)).slice(-2),
          day  = ("0" + date.getDate()).slice(-2);
      return [  mnth, day,date.getFullYear() ].join("/");
      }
  commentmsg(eve)
  {
      this.comments="If any leave taken less than 9 hrs mention the appropriate hours and reason";
      return;
  }
  commentnomsg(eve)
  {
     this.comments="";
      return;
  }
saveVacation(value:Vacation){
    this.msgs = [];
 
   value.employee= this.nav.empdetails.getValue().employeeId;
   value.employeeName= this.nav.empdetails.getValue().employeeName;
   var vacationDateFrom = this.convert(value.vacationDateFrom);
   var vacationDateTo = this.convert(value.vacationDateTo);
   value.vacationDateFrom=vacationDateFrom;
   value.vacationDateTo=vacationDateTo;

   this.addvacation.employee.employeeId=value.employee;
   this.addvacation.employeeName=value.employeeName;
   this.addvacation.vacationDateFrom =value.vacationDateFrom;
   this.addvacation.vacationDateTo =value.vacationDateTo;
   this.addvacation.vacationType =value.vacationType;
   this.addvacation.vacationComments =value.vacationComments;
  
 if((value.vacationDateFrom > value.vacationDateTo))
       {
        this.msgs.push({ severity: 'error', summary: '', detail: "Enter Valid Date Range " });
       }
       else
       {
       this.service.saveVacation(this.addvacation).subscribe(
       response=>{
                  this.msgs.push({ severity: 'success', summary: '', detail: "Vacation Details Added Sucessfully!" });
                 },
                  error => {
                           return false;
                           }
      );
      }
      }

 /*getdisabledDays(){
    this.service.getdisabledDays().subscribe(
           value=>{
                   value.forEach(item=>
                      {
                      let invalidDate = new Date(item.holidays);
                      console.log(invalidDate);
                      
                      this.multiple.push(invalidDate);
                      }
                      )
                     this.invalidDates=this.multiple;
                      })
                  };
                  

}
*/



change(addday) {
    var date = new Date(addday),
        mnth = ("0" + (date.getMonth()+1)).slice(-2),
        day  = ("0" + (date.getDate()+1)).slice(-2);
    return [  date.getFullYear(),mnth, day ].join("-");
    }

 getdisabledDays(){
    this.service.getdisabledDays().subscribe(
           value=>{
                   value.forEach(item=>
                      {
                      let invalidDate = new Date(item.holidays);
                      let invalidDate1 =new Date(this.change(invalidDate));
                      console.log( this.change(invalidDate1));
                      
                      this.multiple.push(invalidDate1);
                      }
                      )
                     this.invalidDates=this.multiple;
                      })
                  };
                  

} 
 



