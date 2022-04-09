import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {LoginService } from './login.service'
import {Employee} from 'app/model/employee';
import { Observable } from "rxjs/Observable";
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng'; 
import {MessageModule} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
import { NavbarService } from "app/navbar.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[ LoginService,MessageService],
  })

export class LoginComponent implements OnInit {

public msgs: MessageModule[] = [];
private empList: any;
constructor(private service: LoginService,private router: Router,private messageService: MessageService,public nav: NavbarService) {}

ngOnInit() {
   this.nav.hide();

 

   }


/*this.nav.sendId() {
    // send message to subscribers via observable subject
    this.nav.sendId(this.empList[0].employeeId);
    console.log("ddd"+this.empList[0].employeeId)
}

clearMessage(): void {
    // clear message
    this.nav.clearMessage();
}*/

login(value:Employee){
    this.msgs = [];
    if (value.employeeId === null || value.password=== null) {
              return Observable.throw("please insert credentials");
    }
    this.service.login(value).subscribe(
         res=>{
                this.empList=res;
                console.log(this.empList[0].employeeId);
                 this.nav.set_empdetails(this.empList[0]);
                 if(this.empList=='' || this.empList == null){
                     this.msgs.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
                     return true;
                  }
                 if(this.empList[0].isActive === 'No'){
                     this.msgs.push({ severity:'error', summary:'', detail:"Required employee is Not Active" });
                     return true;
                 }
                 if(value.employeeId == this.empList[0].employeeId  && value.password.toLowerCase() == this.empList[0].password && this.empList[0].role=='Developer' && this.empList[0].flag==1){
                     this.router.navigateByUrl('/employee/securitycheck');
                     return true;
                 }  
                 else if(value.employeeId.toLowerCase() == this.empList[0].employeeId  && value.password.toLowerCase() == this.empList[0].password && this.empList[0].role=='Business Analyst' && this.empList[0].flag==1){
                     this.router.navigateByUrl('/employee/securitycheck');
                     return true;
                 } 
                 else if(value.employeeId.toLowerCase() == this.empList[0].employeeId  && value.password.toLowerCase() == this.empList[0].password && this.empList[0].role=='Tester' && this.empList[0].flag==1){
                     this.router.navigateByUrl('/tester/testerhome');
                     return true;
                 } 
                 if(value.employeeId.toLowerCase() == this.empList[0].employeeId  && value.password.toLowerCase() == this.empList[0].password && this.empList[0].flag == 0 ){
                     this.router.navigateByUrl('/employee/changepassword');
                     return true;
                 }
                 /*if(value.employeeId.toLowerCase() == this.empList[0].employeeId  && value.password.toLowerCase() == this.empList[0].password && (this.empList[0].flag = 1) ){
                     this.router.navigateByUrl('/employee/securitycheck');
                     return true;
                 }*/
                else
                    {
                    this.msgs.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
                    return true;
                    }},
                    
            error => {
                    this.msgs.push({ severity:'error', summary:'', detail:"Invalid credentials!" });
        }
        );
        }
}