import { Component, OnInit } from '@angular/core';
import { Security } from "app/model/security";
import { Message } from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { SecurityService } from "app/employee/security/security.service";
import { Router, ActivatedRoute } from '@angular/router';
import getUniqueRandomArray from "get-unique-random-array";
import { Question } from "app/model/question";
import { Questioncheck } from "app/model/questioncheck";

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css'],
  providers:[SecurityService]
})
export class SecurityComponent implements OnInit {
count=0;
employeedetails: Employee;
subscription: Subscription;
questionList: any[]=[];
question:Question[]=[];
employeeId: any;
public msgs: Message[] = [];
saveDetails:any[]=[];
public questionAns:Questioncheck[]=[];
answer:any[]=[];
buttonDisable: boolean = true;


  constructor(private service:SecurityService,private router: Router,public nav: NavbarService) {
     this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
  }

  ngOnInit() {
      this.questionList=["","","","","","","","","",""];
     this.getSecdetails();
     
  }
  
  resetPage(){
      this.answer[0]="";
      this.answer[1]="";
      this.answer[2]="";
      this.answer[3]="";
      this.answer[4]="";
      this.answer[5]="";
      this.answer[6]="";
      this.answer[7]="";
      this.answer[8]="";
      this.answer[9]="";
      this.buttonDisable=true;
   
  }
  
  getSecdetails(){
      this.service.getSecurityDetails()
      .subscribe(
              res => {
                  
                  this.question=res;
                  this.questionList=[];
                  this.question.forEach(result =>{
                      this.questionList.push({'value' : result.questionId,'label':result.question});
                  });
              });
}
  
  save(){
      
      this.msgs=[];
      for(var m=0;m<10;m++){
      var saveAns:Questioncheck={
      question:{questionId: null},
      employee:{employeeId:null},
      answers:null,
      
};
      
      saveAns.question.questionId=this.questionList[m].value;
      saveAns.answers=this.answer[m].toUpperCase().trim();
      saveAns.employee.employeeId= this.nav.empdetails.getValue().employeeId;
      this.questionAns[m]=saveAns;
    
  }
      for(var i=0;i<this.answer.length;i++){
      document.getElementById("ans"+i).style.borderColor = "#ccc";
  }
      var flag=0;
      for(var i=0;i<this.answer.length-1;i++){
          for(var j=i+1;j<this.answer.length;j++){
      this.answer[i]=this.answer[i].trim().toUpperCase();
      this.answer[j]=this.answer[j].trim().toUpperCase();
             if(this.answer[i]==this.answer[j]){
                  document.getElementById("ans"+i).style.borderColor = "red";
                  document.getElementById("ans"+j).style.borderColor = "red";
                  flag=1;
      }
          }
      }
      if(flag==1){
      this.msgs=[];
      this.msgs.push({ severity: 'error', summary: '', detail: "Please enter different value in each TextBox!!" });
      if(this.count != 0){
      document.getElementsByTagName("li")[0].innerHTML="";
      }
      this.count++;
     return ;
  }
      
 
      

      this.service.saveDetails(this.questionAns)
      .subscribe(
              res => {
          this.msgs=[];
          this.msgs.push({ severity: 'success', summary: '', detail: "Saved Sucessfully!" });
          return  ;

  },
  
  error => {
    
  });
      
      this.service.saveDetails(this.employeeId).subscribe(
              res => {
              });
      this.router.navigateByUrl('/home');
  }

  change(){
      if (this.answer[0]  && this.answer[1] && this.answer[2] && this.answer[3] && this.answer[4] && this.answer[5]  && this.answer[6]  && this.answer[7] && this.answer[8] && this.answer[9])
          {
          this.buttonDisable=false;
          }
      else{
          
         this.buttonDisable=true;
      }
    } 
  
}
