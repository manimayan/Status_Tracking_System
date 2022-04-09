import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from  '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextareaModule, PanelModule, DropdownModule } from 'primeng/primeng';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { MultiSelectModule, ToggleButtonModule, CalendarModule  } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { Configuration } from '../app.constants';
import { routing } from './employee.routing';
import { CreateemployeeComponent } from './createemployee/createemployee.component';
import { LoginComponent } from "app/employee/login/login.component";
import { ModifyResourceComponent } from './modify-resource/modify-resource.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { LogoutComponent } from './logout/logout.component';
import { SecuritycheckComponent } from "app/employee/securitycheck/securitycheck.component";
import { SecurityComponent } from "app/employee/security/security.component";
import { ResetpasswordComponent } from "app/employee/resetpassword/resetpassword.component";
import { ResetpasswordhomeComponent } from "app/employee/resetpasswordhome/resetpasswordhome.component";
import { ResetquestionComponent } from "app/employee/resetquestion/resetquestion.component";
import { UseremployeeComponent } from "app/employee/useremployee/useremployee.component";
import { ContactComponent } from "app/employee/contact/contact.component";


@NgModule({
  bootstrap: [],
  declarations: [CreateemployeeComponent,LoginComponent, ModifyResourceComponent, ChangepasswordComponent, LogoutComponent, SecurityComponent,SecuritycheckComponent,ResetpasswordComponent,ResetpasswordhomeComponent,ResetquestionComponent,UseremployeeComponent,ContactComponent,],
  imports: [
    HttpModule,
    RouterModule,
    routing,
    FormsModule,
    DataTableModule,
    CommonModule,
    InputTextareaModule,
    PanelModule,
    DropdownModule,
    InputTextModule,
    ButtonModule, 
    DialogModule,
    MultiSelectModule, 
    ToggleButtonModule, 
    CalendarModule,
    ReactiveFormsModule,
    MessagesModule,
  ],
  providers: [Configuration]
})

export class EmployeeModule { }