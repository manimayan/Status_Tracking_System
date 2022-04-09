import { Component,OnInit } from '@angular/core';
import {CreateemployeeService} from './createemployee.service';
import { Message } from 'primeng/primeng';
import { Employee } from "app/model/employee";
import { NavbarService } from 'app/navbar.service';


@Component({
  selector: 'app-createemployee',
  templateUrl: './createemployee.component.html',
  styleUrls: ['./createemployee.component.css',],
  providers:[CreateemployeeService]
})
export class CreateemployeeComponent implements OnInit {
    public updated : boolean = true; 
supervisorName: any;
private supervisordetails:any[] = [];
private supervisorlist:any[]=[];
public msgs: Message[] = [];
constructor(private service:CreateemployeeService, public nav: NavbarService) { }

  ngOnInit() {
      this.nav.show();
      this.getsupervisordetails();
      }
  
  
  addemployee(value:Employee) {
      this.msgs = [];
      this.service.addemployee(value).subscribe(
             
      response=>{
                 this.msgs.push({ severity: 'success', summary: '', detail: "Resource Added Sucessfully!" });
      },
      error => {
          this.msgs.push({ severity: 'error', summary: '', detail: "Enter a valid Fields" });
          return false;
      }); 
      }
  getsupervisordetails()
  {
      this.service.getsupervisordetails().
      subscribe(res => {
          console.log(res);
          this.supervisordetails=res;
          this.supervisorlist.push({'label' : '','value': null});
          this.supervisordetails.forEach(mod => {
              this.supervisorlist.push({'value' : mod,'label':mod});     
                  })
              });
  }
  
  supervisorname(event){

      if (!this.supervisorName)
          {
          this.updated=true;
          }
      else{
          this.updated=false;
      }
  }
  
}
