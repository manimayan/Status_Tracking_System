import { Component, OnInit } from '@angular/core';
import { Security } from "app/model/security";
import { Message } from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { SecuritycheckService } from "app/employee/securitycheck/securitycheck.service";
import { Router, ActivatedRoute } from '@angular/router';
import getUniqueRandomArray from "get-unique-random-array";
import { SecurityComponent } from "app/employee/security/security.component";
import { SecurityService } from "app/employee/security/security.service";
import { Question } from "app/model/question";
import { Observable } from "rxjs/Observable";
import { Questioncheck } from "app/model/questioncheck";
import {Intime} from "app/properties.service";
import { SelectItem } from "primeng/components/common/selectitem";

@Component({

  selector: 'app-securitycheck',
  templateUrl: './securitycheck.component.html',
  styleUrls: ['./securitycheck.component.css'],
providers:[SecuritycheckService]
})

export class SecuritycheckComponent implements OnInit {

   flag:any=0;
   public answer:any[]=[];
   public question:any[]=[];
   public msgs: Message[] = [];
   employeedetails: Employee;
   subscription: Subscription;
   public questionAns:Questioncheck[]=[];
   Qstionvalues :any;
public showSQ : boolean;
public showAD : boolean;
public claimColumn: any[]=[];
public cities: SelectItem[];
public selectedVisibleClaimColumn: any[]=[];
public selectedCities: any[]=[];
private AdminDetails: Employee[] = [];
   constructor(private service:SecuritycheckService, private router: Router,public nav: NavbarService,
          public intime: Intime) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => {
                       this.employeedetails = value;
                       });
}

  ngOnInit() {
      this.getdetails();     
      this.showSQ=true;
      this.showAD=false;
      

      
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
  
  getdetails(){
      this.service.getQuestionDetails(this.employeedetails.employeeId).subscribe(
              
              response=>{
                  
                  for(var i=0;i<this.intime.showingQstions;i++){
                      this.question[i]=response[i].question.question;
                  this.answer[i]=response[i].answers;
              }
    
   });
      
  }
                  
                  forgotanswers(){
                  this.msgs=[];
                  this.showSQ=false;
                  this.showAD=true;
                  }     
                  
                  back(){
                  this.showSQ=true;
                  this.showAD=false;
                  }
                  
                  
                  resetpassword()
                  {
                          var link = "mailto:s.paul.durai.augusta@accenture.com"
                                   + "?cc=kamini.kannan@accenture.com"
                                   + "&subject=" + ("Request to reset security questions")
                                    + "&body=" + (" Hi Admin,  I forgot my password and security questions.Kindly reset my security questions and provide me with a temporary password. My Employee ID is "+this.employeedetails.employeeId)
                          ;

                          window.location.href = link;
                     
                  }  
                  
  resetPage(){
                  this.msgs=[];
                  var x = document.getElementsByTagName("input");
                  for(var i=0;i<x.length;i++){
                      x[i].value="";
                  }
  }
  
  Submit(value:Questioncheck){
                  this.msgs=[];
                  var x = document.getElementsByTagName("input");
                  for(var j=0;j<this.answer.length;j++){
                  x[j].value=x[j].value.toUpperCase().trim();
                  if(this.answer[j]==x[j].value){
                  this.flag=this.flag+1;
                 if(this.flag==this.answer.length){
                  this.router.navigateByUrl('/home');
              }
              }
              }  
                  if(this.flag!=this.answer.length){
                  this.msgs.push({ severity:'error', summary:'', detail:"Invalid answers!" });
              } 
          
              }
                 } 
                  
                  