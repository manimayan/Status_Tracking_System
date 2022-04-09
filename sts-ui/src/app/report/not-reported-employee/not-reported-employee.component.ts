import { Component, OnInit } from '@angular/core';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { ReportemployeeService } from "app/report/not-reported-employee/not-reported-employee.service";
import { Notreportemployee } from "app/model/notreportemployee";
import { NavbarService } from 'app/navbar.service';
import { SelectItem } from "primeng/components/common/selectitem";
import { Subscription } from "rxjs/Subscription";
import { UploadTicket } from "app/model/UploadTicket";


@Component({
    selector: 'app-not-reported-employee',
    templateUrl: './not-reported-employee.component.html',
    styleUrls: ['./not-reported-employee.component.css'],providers:[ReportemployeeService]
  })
export class NotReportedEmployeeComponent implements OnInit {
    subscription: Subscription;
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
    private ReportDetails: Notreportemployee[] = [];

  constructor(private service:ReportemployeeService, public nav: NavbarService) {
      this.subscription=this.nav.getDumpdetails().subscribe(
              value => { 
                       this.Dumpdetails = value; 
                       });
  }

  ngOnInit() {
      this.nav.show();
      this.getdumpdetails();
      this.getEmployeeDetails();
      this.cities = [];
      this.cities.push({label:'Employee Name', value:'Employee Name'});
      this.cities.push({label:'Employee Id', value:'Employee Id'});
      this.cities.push({label:'Email Id', value:'Email Id'});
      this.cities.push({label:'Designation', value:'Designation'});
      this.claimColumn = [
                          
 

  { field:'employeeName',header:'Employee Name',status: true},
  { field:'employeeId',header:'Employee Id',status: true},
  { field:'emailId',header:'Email Id',status: true},
  {field:'designation',header:'Designation', status: true},
  
  ];
      
      for (let i = 0; i < this.claimColumn.length; i++) {
   
       if(this.claimColumn[i].status==true){

          this.selectedVisibleClaimColumn.push(this.claimColumn[i]);
          this.selectedCities.push(this.claimColumn[i].header);
    }  }
      
      
      
     
               
  }
  getEmployeeDetails() {
      this.service.getEmployeeDetails()
          .subscribe(
                  res => {
                      this.ReportDetails = res;
                      console.log(this.ReportDetails) 
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




  getdumpdetails()
  {
  this.createdBy= this.nav.Dumpdetails.getValue().createdBy
  this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }





}
