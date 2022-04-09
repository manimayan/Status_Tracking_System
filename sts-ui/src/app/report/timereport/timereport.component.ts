import { Component, OnInit } from '@angular/core';
import {SelectItem} from 'primeng/primeng';
import {DropdownModule, Message} from 'primeng/primeng';
import { Employee } from "app/model/employee";
import { Timereport } from "app/model/timereport";
import { DatePipe } from '@angular/common'; 
import { TimereportService } from "app/report/timereport/timereport.service";
import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";
@Component({
  selector: 'app-timereport',
  templateUrl: './timereport.component.html',
  styleUrls: ['./timereport.component.css',],
  providers:[TimereportService,DatePipe]
})
export class TimereportComponent implements OnInit {
    public  showReport: boolean;
 notreportedabsence: any;
    nottimeReport: any;
    subscription: Subscription;
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;   
private eName: Employee[] = [];
public cities: SelectItem[];
public selectedVisibleClaimColumn: any[]=[];
public selectedVisibleClaimColumn1: any[]=[];
public claimColumn: any[]=[];
public cols: any[];
public visibleClaimColumn: SelectItem[] = [];
public selectedCities: any[]=[];
selectedCities1: string[] = [];
public selectedClaim: any[] = [];
public msgs: Message[] = [];
columnOptions: SelectItem[];
employeeName: any;
toDate: any;
fromDate: any;
empName: any;
public ename:any []=[];
public result:any[]=[]; 
private timeReport: Timereport[] = [];
public id:any;
public fromdate:any;
public todate:any;

  constructor(private service:TimereportService,private datePipe: DatePipe,public nav: NavbarService ) { 
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
       }
 

  ngOnInit() {
      this.showReport=false;
      this.getdumpdetails();
      this.getNotReportedAbsence();
      this.getnotrepoertedEmployee();
      this.getdetails();
      this.nav.show();
      this.cities = [];
      
      this.cities.push({label:'Employee Id', value:'Employee Id'});
      this.cities.push({label:'Employee Name', value:'Employee Name'});
      this.cities.push({label:'Email Id', value:'Email Id'});
      this.cities.push({label:'Supervisor Name', value:'Supervisor Name'});
      this.cities.push({label:'In Time', value:'In Time'});
      this.cities.push({label:'Out Time', value:'Out Time'});
      this.cities.push({label:'Total Timing', value:'Total Timing'});
      this.cities.push({label:'Date', value:'Date'});
      this.claimColumn = [
                          
{field: 'employeeId', header: 'Employee Id',status: true },
{field: 'employeeName', header: 'Employee Name',status: true },
{field: 'emailId', header: 'Email Id',status: true },
{field: 'supervisorName', header: 'Supervisor Name',status: true },
{field: 'inTime', header: 'In Time',status: true },
{field: 'outTime', header: 'Out Time',status: true },
{field: 'difference', header: 'Total Timing',status: true },
{field: 'date', header: 'Date',status: true },
  ];
      
      for (let i = 0; i < this.claimColumn.length; i++) {
   
       if(this.claimColumn[i].status==true){

          this.selectedVisibleClaimColumn.push(this.claimColumn[i]);
          this.selectedCities.push(this.claimColumn[i].header);
    }  }             
  }

  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }
  Onclick(event){
      this.showReport=true;
  }
  
  getdetails(){
      this.service.getdetails()
      .subscribe(
              res => {
                  this.eName=res;
                  this.result.push({'label' : '','value': null});
                  console.log(res);
                  this.eName.forEach(mod => {
                      this.result.push({'label' : mod.employeeName,'value': mod.employeeId});
   
                  })
                  
                  console.log(this.result)
              });
     
  }
  
 
  
  getNotReportedAbsence(){
      
      this.service.getNotReportedAbsence()
      .subscribe(
      res=>{
          this.notreportedabsence=res;
          console.log(this.notreportedabsence)
      });
     
  }
  
  
  getnotrepoertedEmployee() {
      this.service.getnotrepoertedEmployee()
          .subscribe(
                  res => {
                      this.nottimeReport = res;
                      console.log(this.nottimeReport) 
                  }); 
  }

  submitempdetails(value:Timereport)
  {
          value.fdate= this.datePipe.transform(value.fdate,'yyyy-MM-dd'); 
          value.todate= this.datePipe.transform(value.todate,'yyyy-MM-dd');

      if(value.fdate !=null && value.todate===null){
          this.msgs.push({ severity: 'error', summary: '', detail: "Todate field should not be null" });
      }

      else if(value.todate !=null && value.fdate===null){
          this.msgs.push({ severity: 'error', summary: '', detail: "Fromdate field should not be null" });
      }
      
      else if( value.fdate > value.todate){
          this.msgs.push({ severity: 'error', summary: '', detail: "Enter Valid Date Range " });
      }
     else {
      this.service.submitempdetails(value).subscribe(
              res => {
                  console.log(res)
                   this.timeReport = res;
                  console.log(this.timeReport);
              }); 
     }
  
}
  selecteddisplay()
  {  
     
      this.selectedVisibleClaimColumn=[];
      
      
      
      for(let j = 0; j < this.claimColumn.length; j++){
          for(let i = 0; i <= this.selectedCities.length; i++){
                if(this.claimColumn[j].header == this.selectedCities[i]){
                    this.claimColumn[j].status = true ;
                    break;
                 }else{
                     this.claimColumn[j].status = false;
                 }
                
              }
        }
     
    
      
      for (let i = 0; i < this.claimColumn.length; i++) {
          
          if(this.claimColumn[i].status==true){

             this.selectedVisibleClaimColumn.push(this.claimColumn[i]);
                  
       }  }
         
      
      
      
     
     
  }



}

