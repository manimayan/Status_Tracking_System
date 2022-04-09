import { Component, OnInit } from '@angular/core';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';


import { SelectItem } from "primeng/components/common/api";
import { Ticket } from "app/model/ticket";
import { EditpriorityService } from "app/ticket/editpriority/editpriority.service";
import { NavbarService } from "app/navbar.service";
import { Employee } from "app/model/employee";
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";


@Component({
  selector: 'app-editpriority',
  templateUrl: './editpriority.component.html',
  styleUrls: ['./editpriority.component.css'],providers:[EditpriorityService]
})
export class EditpriorityComponent implements OnInit {
    Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
    temp: number;
    editpripoiry: any;
    activeEmployee: any;
    prioritycheck: any;
    subscription: Subscription;
    employeedetails: Employee;
    empList: any;
    
    prio: SelectItem[];

    private newPriority: any[] = [];
private ticketId: any[] = [];
constructor(private service:EditpriorityService,public nav: NavbarService) { 
   
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
    this.getPriorityDetails();
    this.getdumpdetails();
   
}

/*getPriorityDetails() {
     this.service.getPriorityDetails(this.nav.empdetails.getValue().employeeId)
        .subscribe(
        res => {
            this.empList = res;
            console.log(this.empList) 
        });
  }*/
update(value) {
        this.service.update(this.nav.empdetails.getValue().employeeId,value.ticketId,value.newPriority)
    .subscribe(
        res => {
            this.getPriorityDetails();
            this.newPriority = res;
            console.log(this.newPriority);
            
            
        });
}



getdumpdetails()
{
    this.createdBy= this.nav.Dumpdetails.getValue().createdBy
    this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
}

getPriorityDetails() {
    this.activeEmployee=this.nav.empdetails.getValue().employeeId; 
    this.service.checkeditpriority(this.activeEmployee).subscribe(
            value1 => { 
                this.prio = [];
                     this.editpripoiry = value1; 
                 
                       this.prio.push({ label: 'Select Priority', value: 'Select Priority' });
                     for (let i = 1; i <= 10; i++) {
                         this.temp=0;
                         for ( let entry of this.editpripoiry ) {
                         if(  i== entry){
                             this.temp=1;
                         }
                         }
                         if(this.temp==0){
                             this.prio.push({ label:""+i , value: i }); 
                         }
                        }
                           this.service.getPriorityDetails(this.nav.empdetails.getValue().employeeId)
                     .subscribe(
                     res => {
                         this.empList = res;
                         console.log(this.empList) 
                          
                     
                     });
                       
               
                     });

  }


}

