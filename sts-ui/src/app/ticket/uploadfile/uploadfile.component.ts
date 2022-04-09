

import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

import { Router } from '@angular/router';


import { RouterModule, Routes } from '@angular/router';
import { NavbarService } from "app/navbar.service";
import { uploadfileservice } from "app/ticket/uploadfile/uploadfile.service";
//import { FileUploader } from 'ng2-file-upload';
import {Upload} from 'app/model/Upload';
import { Message } from "primeng/components/common/message";
import { Subscription } from "rxjs/Subscription";
import { Employee } from "app/model/employee";
import { UploadTicket } from "app/model/UploadTicket";

@Component({
  selector: 'app-uploadfile',
  templateUrl: './uploadfile.component.html',
  styleUrls: ['./uploadfile.component.css'],
  providers:[uploadfileservice]
})
export class UploadfileComponent implements OnInit {
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    employeedetails: Employee;
    subscription: Subscription;
    msgs: any;
public updated : boolean = true; 
inputfile: any;

    uploadClaimUsageLog: any;
   
  
    public files:any[]=[];  
filesToUpload: FormData;
public uploadfile:Upload[]=null;



@ViewChild('fileInput') myFileInput: ElementRef;

private formData: FormData = new FormData();
public errorMsg: Message[] = [];


private displaySuccessmessage = false;
private displayErrorMessage = false;

private message: any;
private errorPath = false;
private displayFileNameError = false;
public fileName;
public display = false;
public loading = false;
  constructor(private router: Router,private service:uploadfileservice, private nav: NavbarService) { 
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
      this.nav.show();
      this.errorMsg = [];
      this.msgs=[];
    
  }
      displayType() {
          this.display = true;
          this.displaySuccessmessage = false;
          this.displayErrorMessage = false;
          this.displayFileNameError = false;
      }

      okClick() {
          this.displaySuccessmessage = false;
          this.message = "";
      }

      fileEvent(event) {
          this.errorMsg = [];
          this.formData = new FormData();
          let fileList: FileList = event.target.files;
          if (fileList.length > 0) {
         
              let file: File = fileList[0];
              this.fileName = file.name;
              this.formData.append('uploadFile', file, file.name);
              this.display = true;
          }
          
          if (!this.inputfile)
          {
          this.updated=true;
          }
      else{
          this.updated=false;
      }
      
          
      }

      getdumpdetails()
      {
          this.createdBy= this.nav.Dumpdetails.getValue().createdBy
          this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
      }

      submit() {
          this.errorMsg = [];
          this.loading = true;
          this.service.uploadfile(this.formData,this.nav.empdetails.getValue().employeeName).subscribe(
                  response => {
                   
              console.log(response);
              this.uploadfile=response;
              
              this.errorMsg.push({ severity: 'success', summary: '', detail: "Uploaded successfully!" });
                
              },
             
              error => {
                  this.errorMsg = [];
                  this.loading = false;
                  this.errorMsg.push({ severity: 'error', summary: '', detail: 'Please check the Ticket Id (Ticket Id should be Unique) and Application name should exist in the database. Unable to process the uploaded file. Please upload again' });
              }
          );
      }
}