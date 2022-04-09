import { NgModule,CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Configuration } from '../app.constants';
import { Message } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { routing } from './ticket.routing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as $ from 'jquery';
import { PanelModule, DropdownModule } from 'primeng/primeng';
import { DataTableModule } from 'primeng/primeng';
import { MultiSelectModule } from 'primeng/primeng';
import {ScheduleModule, DialogModule, CalendarModule, ToggleButtonModule, DragDropModule, 
ButtonModule, InputTextareaModule, CheckboxModule, InputTextModule, SelectButtonModule} from 'primeng/primeng';
import { AssignticketComponent } from "app/ticket/assignticket/assignticket.component";
import { EditpriorityComponent } from "app/ticket/editpriority/editpriority.component";
import { ReleaseticketComponent } from "app/ticket/releaseticket/releaseticket.component";
import { DeleteticketComponent } from "app/ticket/deleteticket/deleteticket.component";
import { AddticketComponent } from "app/ticket/addticket/addticket.component";
import { ReassignticketComponent } from './reassignticket/reassignticket.component';
import { MyclosedticketComponent } from './myclosedticket/myclosedticket.component';
import { UploadfileComponent } from "app/ticket/uploadfile/uploadfile.component";
import { FileUploadModule } from "primeng/components/fileupload/fileupload";
import { AllclosedticketComponent } from './allclosedticket/allclosedticket.component';


@NgModule({
  bootstrap: [],
  declarations: [AssignticketComponent,EditpriorityComponent,ReleaseticketComponent,DeleteticketComponent, AddticketComponent, ReassignticketComponent, MyclosedticketComponent,UploadfileComponent, AllclosedticketComponent],
  imports: [
   RouterModule,
   routing,
   CommonModule,
   ScheduleModule,
   HttpModule,
   FormsModule,
   DataTableModule,
   PanelModule,
   DropdownModule,
   DialogModule,
   MultiSelectModule, 
   ToggleButtonModule, 
   CalendarModule,
   FileUploadModule, 
   ReactiveFormsModule,
   MessagesModule,DragDropModule, ButtonModule, InputTextareaModule, CheckboxModule, InputTextModule, SelectButtonModule
  ],
  providers: [Configuration],
   schemas: [CUSTOM_ELEMENTS_SCHEMA],
})

export class TicketModule { }