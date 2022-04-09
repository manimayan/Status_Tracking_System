import { Component, OnInit } from '@angular/core';

import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { ListemployeeService } from './listemployee.service';
import { Employee } from 'app/model/employee';

@Component({
  selector: 'app-listemployee',
  templateUrl: './listemployee.component.html',
  styleUrls: ['./listemployee.component.css'],providers:[ListemployeeService]
})
export class ListemployeeComponent implements OnInit {
    private empList: Employee[] = [];
  constructor(private service:ListemployeeService) { }

  ngOnInit() {
      this.getEmployeeDetails();
  }

  getEmployeeDetails() {
      this.service.getEmployeeDetails()
          .subscribe(
          res => {
              this.empList = res;
          });
  }
}
