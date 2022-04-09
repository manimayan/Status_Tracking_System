import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/Observable";
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 
import { ResetpasswordhomeService } from "app/employee/resetpasswordhome/resetpasswordhome.service";
import { Employee } from "app/model/employee";
import { NavbarService } from 'app/navbar.service';
import { Subscription } from "rxjs/Subscription";
import { Questioncheck } from "app/model/questioncheck";
import { Intime } from "app/properties.service";
import { SelectItem } from "primeng/components/common/selectitem";

@Component({
  selector: 'app-resetpasswordhome',
  templateUrl: './resetpasswordhome.component.html',
  styleUrls: ['./resetpasswordhome.component.css'],
  providers:[ResetpasswordhomeService]
})
export class ResetpasswordhomeComponent implements OnInit {
    
employeedetails: Employee;
subscription: Subscription;
public msgs: Message[] = [];
public messages: Message[] = [];
private empList: any;
private empId:any;
private data:any;
public showQuestions : boolean;
public showRestpassword : boolean;
public showAdminDetails : boolean;
variable:any;
public questionAns:Questioncheck[]=[];
flag:any=0;
public answer:any[]=[];
public question:any[]=[];
employeeId:any;

public claimColumn: any[]=[];
public cities: SelectItem[];
public selectedVisibleClaimColumn: any[]=[];
public selectedCities: any[]=[];
private AdminDetails: Employee[] = [];

  constructor(private service: ResetpasswordhomeService,private router: Router,public nav: NavbarService, public intime: Intime) {
  }

  ngOnInit() {
      this.showQuestions=false;
      this.showRestpassword=true;
      this.showAdminDetails=false;
      

      
      this.getAdminDetails();
      
      this.cities = [];
      this.cities.push({label:'Employee Name', value:'Employee Name'});
      this.cities.push({label:'Employee Id', value:'Employee Id'});
      this.cities.push({label:'Email Id', value:'Email Id'});
      this.cities.push({label:'Contact Number', value:'Contact Number'});
      this.claimColumn = [
                          
 

  { field:'employeeName',header:'Employee Name',status: true},
  { field:'employeeId',header:'Employee Id',status: true},
  { field:'emailId',header:'Email Id',status: true},
  {field:'contactNumber',header:'Contact Number', status: true},
  
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
  
  
  resetpasswordhome(value:Employee){
      this.showQuestions=false;
      this.msgs = [];
      this.messages = [];
      this.service.resetpasswordhome(value).subscribe(
              res=>{
                  this.empList=res;
                  this.nav.set_empdetails(this.empList[0]);
                   if(this.empList=='' || this.empList == null){
                       this.messages.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
                       return true;
                    }
                   if(this.empList[0].isActive === 'No'){
                       this.messages.push({ severity:'error', summary:'', detail:"Required employee is Not Active" });
                       return true;
                   }
                   if(value.employeeId.toLowerCase() == this.empList[0].employeeId  && this.empList[0].role=='Developer' || this.empList[0].role=='Business Analyst' ||  this.empList[0].role=='Tester' && this.empList[0].flag==1){                     
                       this.subscription=this.nav.get_empdetails().subscribe(
                               value => { 
                                        this.employeedetails = value; 
                                        });
                       this.getdetails();
                       return true;
                   }  
                
                  else
                      {
                      this.messages.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
                      return true;
                      }},
                      
              error => {
                      this.messages.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
          }
          );
          } 
  
  getdetails(){
      
      
      
this.service.getQuestionDetails(this.employeedetails.employeeId).subscribe(
              
              response=>{
                  if(response.length==0)
                  {
                  this.messages.push({ severity:'error', summary:'', detail:"Login to sts and setup your security questions. For temporary password contact your Admin!" });
                  }
                  
                  else{
                  for(var i=0;i<this.intime.showingQstions;i++){
                      this.question[i]=response[i].question.question;
                  this.answer[i]=response[i].answers;
                  this.showQuestions=true;
                  }
              }
    
   });
      
                  } 
      
  resetPage(){
       var x = document.getElementsByTagName("input");
       for(var i=0;i<x.length;i++){
           x[i].value="";
       }
                  this.employeeId="";
       this.showQuestions=false;
  }
  
                  resetpassword(empid : String)
                  {
                          var link = "mailto:s.paul.durai.augusta@accenture.com"
                                   + "?cc=kamini.kannan@accenture.com"
                                   + "&subject=" + ("Request to reset security questions")
                                    + "&body=" + (" Hi Admin,  I forgot my password and security questions.Kindly reset my security questions and provide me with a temporary password. My Employee ID is "+ empid)
                          ;

                          window.location.href = link;
                     
                  }   
                  
                  
                  back(){
                  this.showQuestions=false;
                  this.showRestpassword=true;
                  this.showAdminDetails=false;
                  
                  }
                  
                  forgotans(){
                  this.msgs = [];
                  this.messages = [];
                  this.showQuestions=false;
                  this.showRestpassword=true;
                  this.showAdminDetails=true;
                  }

                  
  Submit(value:Questioncheck){
      
      this.msgs = [];
      this.messages = [];
      
      var x = document.getElementsByTagName("input");
      for(var j=0;j<this.answer.length;j++){
      var i=j+1;
      x[i].value=x[i].value.toUpperCase().trim();
      if(this.answer[j]==x[i].value){
      this.flag=this.flag+1;
     if(this.flag==this.answer.length){
      this.router.navigateByUrl('/employee/resetpassword');
  }
  }
  }  
      if(this.flag!=this.answer.length){
      this.msgs.push({ severity:'error', summary:'', detail:"Invalid answers!" });
  } 

  }
  
 
}
