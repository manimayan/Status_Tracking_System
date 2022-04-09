import { Component, OnInit } from '@angular/core';

import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 


import {SelectItem} from 'primeng/primeng'; 
import { Nochange } from "app/model/nochange";
import { ListnochangeService } from "app/report/nochange/nochange.service";
import { NavbarService } from "app/navbar.service";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";
declare let jsPDF;
@Component({
  selector: 'app-nochange',
  templateUrl: './nochange.component.html',
  styleUrls: ['./nochange.component.css'],
providers:[ListnochangeService ]
})
export class NochangeComponent implements OnInit {
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

    public msgs: Message[] = [];






    columnOptions: SelectItem[];

        private nochangeList : Nochange[] = [];


    constructor(private service:ListnochangeService,public nav:NavbarService ) { 
        this.subscription=this.nav.getDumpdetails().subscribe(
                value => { 
                         this.Dumpdetails = value; 
                         });
    }

    ngOnInit() {
        this.getnochangeDetails();
        this.nav.show();
        this.getdumpdetails();
        this.cities = [];      
        this.cities.push({label:'Ticket Id', value:'Ticket Id'});
        this.cities.push({label:'Employee Name', value:'Employee Name'});
        this.cities.push({label:'Ticket Type', value:'Ticket Type'});
        this.cities.push({label:'Description', value:'Description'});
        this.cities.push({label:'Application', value:'Application'});
        this.cities.push({label:'Priority', value:'Priority'});
        this.cities.push({label:'Activity', value:'Activity'});
        this.cities.push({label:'Status', value:'Status'});
        this.cities.push({label:'Updated On', value:'Updated On'});
        this.cities.push({label:'dev Comment', value:'dev Comment'});
        
        this.claimColumn = [
                            
    { field:'ticketId', header:'Ticket Id',status: true },

    { field:'employeeName',header:'Employee Name',status: true},
    { field:'ticketType',header:'Ticket Type',status: true},
    { field:'ticketDescription',header:'Description',status: true},
    {field:'applicationId',header:'Application', status: true},
    { field:'priority',header:'Priority',status: true},
    { field:'activity',header:'Activity',status: true},
    { field:'status',header:'Status',status: true},
    { field:'updatedOn',header:'Updated On',status: true},
    { field:'devComment',header:'dev Comment',status: true}
    ];
        
        for (let i = 0; i < this.claimColumn.length; i++) {
     
         if(this.claimColumn[i].status==true){

            this.selectedVisibleClaimColumn.push(this.claimColumn[i]);
            this.selectedCities.push(this.claimColumn[i].header);
      }  }
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



getnochangeDetails() {
    this.service.getnochangeDetails()
        .subscribe(
        res => {
           
    
                     /* res.forEach(item=>{
                          var date = this.change(item.updatedOn);
                          console.log(date);
                          item.updatedOn=date;
                      })*/
                      
                      this.nochangeList = res;
                      console.log(this.nochangeList);
        });
}
}
