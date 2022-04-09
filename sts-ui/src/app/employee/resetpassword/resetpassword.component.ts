import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/Observable";
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 
import { ResetpasswordService } from "app/employee/resetpassword/resetpassword.service";
import { Employee } from "app/model/employee";
import { NavbarService } from 'app/navbar.service';
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css'],
  providers:[ResetpasswordService]
})
export class ResetpasswordComponent implements OnInit {
    employeedetails: Employee;
    subscription: Subscription;

public msgs: Message[] = [];
private empList: any;
private empId:any;
private data:any;
  constructor(private service: ResetpasswordService,private router: Router,public nav: NavbarService) {
      this.subscription=this.nav.get_empdetails().subscribe(
              value => { 
                       this.employeedetails = value; 
                       });
  }

  ngOnInit() {
  }
  resetpassword(value:Employee){
      this.msgs = [];
     
    if(value.newpassword===value.reenterpassword)
         {
         this.service.resetpassword(this.nav.empdetails.getValue().employeeId,value)
         .subscribe(
                 res=>{
                     this.empList=res;
                    {
                         this.msgs.push({ severity:'success', summary:'', detail:"password changed successfully" });
                        
                             this.router.navigateByUrl('/employee/logout');
                      
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
