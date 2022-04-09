import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/Observable";
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 
import { ChangepasswordService } from "app/employee/changepassword/changepassword.service";
import { Employee } from "app/model/employee";
import { NavbarService } from 'app/navbar.service';
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css'],
  providers:[ChangepasswordService]
})
export class ChangepasswordComponent implements OnInit {
    employeedetails: Employee;
    subscription: Subscription;

public msgs: Message[] = [];
private empList: any;
private empId:any;
private data:any;
  constructor(private service: ChangepasswordService,private router: Router,public nav: NavbarService) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
  }

  ngOnInit() {
  }
  changepassword(value:Employee){
      this.msgs = [];
     
     if(value.password===value.newpassword){   
         this.msgs.push({ severity:'error', summary:'', detail:"old password and new password cannot be same" });
         return true;
         }
    
     else if(value.newpassword===value.reenterpassword)
         {
         this.service.changepassword(this.nav.empdetails.getValue().employeeId,value)
         .subscribe(
                 res=>{
                     this.empList=res;
                     if(this.empList.password!=value.password){
                      
                         this.msgs.push({ severity:'error', summary:'', detail:"Old password did not match" });
                         return true;
                     }
                     else{
                         this.msgs.push({ severity:'success', summary:'', detail:"password changed successfully" });
                        
                             this.router.navigateByUrl('/employee/security');
                      
                         return true;
                     }
                 }
         );
         }
     else{
         this.msgs.push({ severity:'error', summary:'', detail:"New password and Re-enter password should be same !" });
     }
  }
  clear()
  {
      
  }

}
