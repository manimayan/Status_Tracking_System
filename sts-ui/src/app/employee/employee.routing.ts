import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { CreateemployeeComponent } from './createemployee/createemployee.component';
import {      LoginComponent     } from './login/login.component';
import { ModifyResourceComponent } from "app/employee/modify-resource/modify-resource.component";
import { ChangepasswordComponent } from "app/employee/changepassword/changepassword.component";
import { LogoutComponent } from "app/employee/logout/logout.component";
import { SecurityComponent } from "app/employee/security/security.component";
import { SecuritycheckComponent } from "app/employee/securitycheck/securitycheck.component";
import { ResetpasswordComponent } from "app/employee/resetpassword/resetpassword.component";
import { ResetpasswordhomeComponent } from "app/employee/resetpasswordhome/resetpasswordhome.component";
import { ResetquestionComponent } from "app/employee/resetquestion/resetquestion.component";
import { UseremployeeComponent } from "app/employee/useremployee/useremployee.component";
import { ContactComponent } from "app/employee/contact/contact.component";

export const routes: Routes = [{path:'',
                               children:[
                                         { path : '' , component : LoginComponent },
                                         { path: 'createemployee', component : CreateemployeeComponent},
                                         { path: 'modifyemployee', component : ModifyResourceComponent},
                                         { path: 'changepassword', component : ChangepasswordComponent},
                                         {path: 'logout', component :LogoutComponent},
                                         {path: 'login', component :LoginComponent },
                                         {path: 'security' ,  component:SecurityComponent},
                                         {path: 'securitycheck' ,  component:SecuritycheckComponent},
                                         {path: 'resetpassword' ,  component:ResetpasswordComponent},
                                         {path: 'resetpasswordhome' ,  component:ResetpasswordhomeComponent},
                                         {path: 'resetquestion', component :ResetquestionComponent },
                                         {path: 'useremployee', component :UseremployeeComponent },
                                         {path: 'contact', component :ContactComponent },
                                         
                                         
                                         ]}
                                         ];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);