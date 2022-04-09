import { Component, OnInit } from '@angular/core';
import { ListnochangeService } from "app/report/nochange/nochange.service";
import { NavbarService } from "app/navbar.service";
import { DashboardService } from "app/dashboard/dashboard.service";
import { Employee } from "app/model/employee";
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[DashboardService ]
})
export class DashboardComponent implements OnInit {

    vacation:any;
    progress:any;
    getworkprog :any[]= [];
    getunplannedvac:any[]= [];
    getplannedvac:any[]= [];
    contactno: any;
    skills: any;
    mailId: any;
    designation: any;
    employeeName: any;
    employeeDetails: Employee;
    subscription: Subscription;

constructor(private service:DashboardService,public nav:NavbarService ) { 
      
      this.subscription = this.nav.get_empdetails().subscribe(
      value=>{
          this.employeeDetails=value;    
      });

}

  ngOnInit() {
      this.nav.hide();
      this.employeeInfo();  
      this.getworkprogress();
      this.getplannedvacation();
      this.getunplannedvacation();
      this.progress = {
              labels: [ "Approved","Inprogress", "Onhold","Closed",],
              color: ["white"],
              datasets: [
                  {
                      data: [3,4,6,7],
                      backgroundColor: [
                          "#FF6384",
                          "#36A2EB",
                          "#FFCE56",
                          "#9ccc65"
                      ],
                      hoverBackgroundColor: [
                          "#FF6384",
                          "#36A2EB",
                          "#FFCE56",
                          "#9ccc65"
                      ]
                  }]    
              };

      this.vacation = {
              labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July','August','September','October','November','December'],
              datasets: [
                  {
                      label: 'Planned Vacation',
                      backgroundColor: '#42A5F5',
                      borderColor: '#1E88E5',
                      data: [2, 4, 5, 2, 6, 7, 8, 6, 7, 12, 5, 2]
                  },
                  {
                      label: 'UnPlanned vacation',
                      backgroundColor: '#9CCC65',
                      borderColor: '#7CB342',
                      data: [3, 5, 1, 4, 3, 5, 7, 6, 3, 3, 5, 6]
                  }
              ]
          }
  }

employeeInfo()
{
    this.employeeName =this.nav.empdetails.getValue().employeeName;
    this.designation=this.nav.empdetails.getValue().designation;
  //  this.mailId=this.nav.empdetails.getValue().emailId; 
    this.skills=this.nav.empdetails.getValue().skills;
   // this.contactno=this.nav.empdetails.getValue().contactNumber;
}

getworkprogress()
{
    this.service.getworkprogress(this.nav.empdetails.getValue().employeeId)
    .subscribe(
        res => {
            this.getworkprog = res[0];
            console.log(this.getworkprog);
            });      
}

getplannedvacation()
{
    this.service.getplannedvacation(this.nav.empdetails.getValue().employeeId)
    .subscribe(
        res => {
            this.getplannedvac = res[0];
            console.log(this.getplannedvac);
            });      
}
getunplannedvacation()
{
    this.service.getunplannedvacation(this.nav.empdetails.getValue().employeeId)
    .subscribe(
        res => {
            this.getunplannedvac = res[0];
            console.log(this.getunplannedvac);
            });      
}

}
