import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/primeng';
import { ActivatedRoute, Router } from '@angular/router';

import { DocumentationService } from "app/report/documentation/documentation.service";
import { Documentation } from "app/model/documentation";
import { NavbarService } from "app/navbar.service";
import { Employee } from "app/model/employee";
import { Homedoc } from "app/model/Homedoc";
import { UploadTicket } from "app/model/UploadTicket";
@Component({
  selector: 'app-documentation',
  templateUrl: './documentation.component.html',
  styleUrls: ['./documentation.component.css'],
providers:[DocumentationService]
})
export class DocumentationComponent implements OnInit {
    documentationComment: any;
    subscription: any;
    employeedetails: Employee;
    public msgs: Message[] = [];
Dumpdetails: UploadTicket;
public createdBy : string;
public createdOn : Date;
private homedocList: Homedoc[] = [];
    
public remedy:any;
public ticketId:any;
public documentationDescription:any;
public resultData:any
    
  constructor(private service:DocumentationService,private router: Router,private activatedRoute:ActivatedRoute,public nav: NavbarService) {
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
   
      this.getdumpdetails();
      this.activatedRoute.queryParams.subscribe(params => {
          let ticketId = params['ticketId'];
          this.ticketId=ticketId;
          let remedy = params['remedy'];
          this.remedy=remedy;
          let documentationDescription = params['documentationDescription'];
          this.documentationDescription=documentationDescription;
          let documentationComment = params['documentationComment'];
          this.documentationComment=documentationComment;
          
          
          
                 }); 
      
  }

  getdumpdetails()
  {
      this.createdBy= this.nav.Dumpdetails.getValue().createdBy
      this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
  }

  
  getdocdetails() {
      this.service.getClosedDetails(this.nav.empdetails.getValue().employeeId)
          .subscribe(
          res => {
            
              this.homedocList = res;
              console.log(res);
              this.msgs.push({ severity: 'success', summary: '', detail: "Sucessfully updated!" });
              
          },
          error => {
              return false;
             
          });
  }
  
  savedocument(value:Documentation,ticketId :any,documentationId:any){
      this.msgs = []; 
      
     
      this.service.saveDocument(value,ticketId).subscribe(
          response=>{
                  this.msgs.push({ severity: 'success', summary: '', detail: "Documentation Details Added Sucessfully!" });
                  this.router.navigateByUrl('/home');
          },
          error => {
              return false;
          }
  );


}

  
  fClear(){
      this.activatedRoute.queryParams.subscribe(params => {
          let ticketId = params['ticketId'];
          this.ticketId=ticketId;
          let remedy = params['remedy'];
          this.remedy=remedy;
          let documentationDescription = params['documentationDescription'];
          this.documentationDescription=documentationDescription;
          let documentationComment = params['documentationComment'];
          this.documentationComment=documentationComment;
          
         
      if( (this.remedy==null))
  
  {
          this.documentationDescription = null;
          this.documentationComment= null;
      
  }
      else{
          this.documentationComment= null;
          
   }
      });
 }
  
}
