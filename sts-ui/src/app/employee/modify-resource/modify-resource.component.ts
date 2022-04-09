import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/primeng';
import { NavbarService } from 'app/navbar.service';
import { Employee } from "app/model/employee";
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextareaModule, PanelModule, DropdownModule } from 'primeng/primeng';
import { MultiSelectModule, ToggleButtonModule, CalendarModule  } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { modifyresourceservice } from "app/employee/modify-resource/modify-resource.service";


@Component({
  selector: 'app-modify-resource',
  templateUrl: './modify-resource.component.html',
  styleUrls: ['./modify-resource.component.css'],
  providers:[ modifyresourceservice]
})
export class ModifyResourceComponent implements OnInit {
    designation: any;
skills: any;
    private supervisordetails:any[] = [];
private supervisorlist:any[]=[];
    supervisorName: any;
    isSupervisor: any;
    isActive: any;
    role: any;
    report: any;
    isInclude: any;
    isAdmin: any;
   public empDetails: any[]=[];
    public msgs: Message[] = [];
public ename:any []=[];
private empList: Employee[] = [];
public result:any[]=[];
public empname:any[]=[];
public updated : boolean;



constructor(private service: modifyresourceservice,public nav: NavbarService) { }


  ngOnInit() {  
      this.updated=false; 
      this.nav.show();
      this.getsupervisordetails();
      this.getdetails();
  }
  

  
  saveaddresource(value:Employee){
      this.msgs=[];
      alert(value.isAdmin)
      this.service.saveaddresource(value).subscribe(
          response=>{
                  this.msgs.push({ severity: 'success', summary: '', detail: "Resource Modified  Sucessfully!" });
          },
          error => {
              return false;
              
          }
  );


}
  
  getsupervisordetails()
  {
      this.service.getsupervisordetails().
      subscribe(res => {
          this.supervisordetails=res;
          this.supervisorlist.push({'label' : '','value': null});
          this.supervisordetails.forEach(mod => {
              this.supervisorlist.push({'value' : mod,'label':mod});     
                  })
                    console.log(this.supervisorlist)
              });
    
  }
  
  getdetails(){
      this.service.getEmployeeDetails()
      .subscribe(
              res => {
                  this.ename=res;
                  this.result.push({'label' : '','value': null});
                  this.ename.forEach(mod => {
                      this.result.push({'label' : mod.employeeName, 'value': mod.employeeId});
                  })
                  console.log(res)
              });
      
  }
  OnSelect(value) {
      this.service.employeeDetails(value)
       .subscribe(
               res=>{
                       this.empDetails = res; 
                       this.isAdmin=res.isAdmin;
                       this.isInclude=res.isInclude;
                       if(res.report==1){
                          
                           this.report='Yes'
                       }
                       else{
                         
                           this.report='No'
                       }
                       this.role=res.role;
                       if (res.role=='Tester')
                                              {
                                                  this.updated=true;
                                              }
                                              else
                                              {
                                                  this.updated=false;
                                              }  
                       this.isActive=res.isActive;
                       this.isSupervisor=res.isSupervisor;
                       this.supervisorName=res.supervisorName;
                       this.designation=res.designation;
                     this.skills=res.skills;
                                           
                      
               },
               error => {
                    return false;
               }
       );
  }

}
  
  /*OnSelect(value){
      alert("into function")
      console.log(value);
      
    }
  */
  
