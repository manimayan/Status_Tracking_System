import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AssignticketComponent } from 'app/ticket/assignticket/assignticket.component';
import { EditpriorityComponent } from 'app/ticket/editpriority/editpriority.component';
import { ReleaseticketComponent } from "app/ticket/releaseticket/releaseticket.component";
import { DeleteticketComponent } from "app/ticket/deleteticket/deleteticket.component";
import { AddticketComponent } from "app/ticket/addticket/addticket.component";
import { ReassignticketComponent } from 'app/ticket/reassignticket/reassignticket.component';
import { MyclosedticketComponent } from "app/ticket/myclosedticket/myclosedticket.component";
import { UploadfileComponent } from "app/ticket/uploadfile/uploadfile.component";
import { AllclosedticketComponent } from "app/ticket/allclosedticket/allclosedticket.component";

export const routes: Routes = [{path:'',children:[
                               { path: '', component : AssignticketComponent},
                               { path: 'editpriority', component:  EditpriorityComponent},
                               { path: 'releaseticket', component:  ReleaseticketComponent},
                               { path: 'deleteticket', component:  DeleteticketComponent},
                               { path: 'addticket', component:  AddticketComponent},
                               { path: 'reassignticket', component : ReassignticketComponent},
                               { path: 'myclosed', component:MyclosedticketComponent },
                               { path: 'allclosed', component:AllclosedticketComponent },
                               { path: 'uploadfile', component:UploadfileComponent }
                               ]}
                               ];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
