import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/Observable";
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 
import { Employee } from "app/model/employee";
import { NavbarService } from 'app/navbar.service';
import { Subscription } from "rxjs/Subscription";
import { ContactService } from "app/employee/contact/contact.service";
import { escape } from "querystring";
import { SelectItem } from "primeng/components/common/selectitem";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
  providers:[ContactService]
})
export class ContactComponent implements OnInit {
    employeedetails: Employee;
    subscription: Subscription;
public claimColumn: any[]=[];
public cities: SelectItem[];
public selectedVisibleClaimColumn: any[]=[];
public selectedCities: any[]=[];
private AdminDetails: Employee[] = [];

  constructor(private service: ContactService,private router: Router,public nav: NavbarService) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
  }

  ngOnInit() {
      
      this.getAdminDetails();
      
      this.cities = [];
      this.cities.push({label:'Employee Name', value:'Employee Name'});
      this.cities.push({label:'Employee Id', value:'Employee Id'});
      
    //for security reason this two field commented (Kavitha Naganathan)
      /*this.cities.push({label:'Email Id', value:'Email Id'});
      this.cities.push({label:'Contact Number', value:'Contact Number'});*/
      
      this.claimColumn = [
                          
 

  { field:'employeeName',header:'Employee Name',status: true},
  { field:'employeeId',header:'Employee Id',status: true},
  
//for security reason this two field commented (Kavitha Naganathan)
 /* { field:'emailId',header:'Email Id',status: true},
  {field:'contactNumber',header:'Contact Number', status: true},*/
  
  ];
      
      for (let i = 0; i < this.claimColumn.length; i++) {
          
          if(this.claimColumn[i].status==true){

             this.selectedVisibleClaimColumn.push(this.claimColumn[i]);
             this.selectedCities.push(this.claimColumn[i].header);
       }  }
      
  }
  
  getAdminDetails() {
      this.service.getAdminDetails()
          .subscribe(
                  res => {
                      this.AdminDetails = res;
                      console.log(this.AdminDetails) 
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
