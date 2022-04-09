import { Component, OnInit } from '@angular/core';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { DailystatusreportService } from "app/report/dailystatusreport/dailystatusreport.service";
import { NavbarService } from "app/navbar.service";
import { SelectItem } from "primeng/components/common/selectitem";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";



@Component({
  selector: 'app-dailystatusreport',
  templateUrl: './dailystatusreport.component.html',
  styleUrls: ['./dailystatusreport.component.css'],providers:[DailystatusreportService]
})
export class DailystatusreportComponent implements OnInit {
    subscription: Subscription;
   
    notreported: any;
    statusDetails: any;
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
public cities: SelectItem[];
public selectedVisibleClaimColumn: any[]=[];
public selectedVisibleClaimColumn1: any[]=[];
public claimColumn: any[]=[];
public cols: any[];
public visibleClaimColumn: SelectItem[] = [];
public selectedCities: any[]=[];
selectedCities1: string[] = [];
public selectedClaim: any[] = [];
columnOptions: SelectItem[];

  


  constructor(private service:DailystatusreportService,public nav: NavbarService) { 
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.getNotReported();
      this.getdumpdetails();
     this.getDailystatusreport();
     this.nav.show();

     
     this.cities = [];
     this.cities.push({label:'Employee Name', value:'Employee Name'});
     this.cities.push({label:'Ticket Id', value:'Ticket Id'});
     
     this.cities.push({label:'Ticket Type', value:'Ticket Type'});
     this.cities.push({label:'Description', value:'Description'});
     this.cities.push({label:'Application', value:'Application'});
     this.cities.push({label:'Priority', value:'Priority'});
     this.cities.push({label:'Activity', value:'Activity'});
     this.cities.push({label:'Status', value:'Status'});
  
     this.cities.push({label:'dev Comment', value:'dev Comment'});
     this.cities.push({label:'Updated On', value:'Updated On'});
     this.cities.push({label:'Tester', value:'Tester'});
     this.cities.push({label:'test Comment', value:'test Comment'});
     this.cities.push({label:'Worked Today', value:'Worked Today'});
     
     
     this.claimColumn = [
                         {field: 'employeeName', header: 'Employee Name',status: true},
                         {field: 'ticketId', header: 'Ticket Id',status: true},
                         {field: 'ticketType', header: 'Ticket Type',status: true},
                         {field: 'ticketDescription', header: 'Description',status: true},
                         {field: 'applicationName', header: 'Application',status: true},
                         {field: 'priority', header: 'Priority',status: true},
                         {field: 'activity', header: 'Activity',status: true},
                         {field: 'status', header: 'Status',status: true},
                        
                         {field: 'devComment', header: 'Dev Comment',status: true},
                         {field: 'updatedOn', header: 'UpdatedOn',status: true},
                         {field: 'tester', header: 'Tester',status: true},
                         {field: 'testComment', header: 'Test Comment',status: true},
          {field: 'workedOnToday', header: 'Worked Today',status: true}];
                         
  
  
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
  
  getNotReported(){
     
      this.service.getNotReported()
      .subscribe(
      res=>{
          this.notreported=res;
          console.log(this.notreported)
      });
     
  }
  getDailystatusreport() {
    
      this.service.getDailystatusreport()
         .subscribe(
         res => {
             /*res.forEach(item=>{
                 var date = this.change(item.updatedOn);
                 item.updatedOn=date;
             })*/
             this.statusDetails = res;
         });
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
