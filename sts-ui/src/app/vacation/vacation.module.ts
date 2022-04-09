import { NgModule,CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Configuration } from '../app.constants';
import { Message } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { routing } from './vacation.routing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as $ from 'jquery';
import { PanelModule, DropdownModule } from 'primeng/primeng';
import { DataTableModule } from 'primeng/primeng';
import { MultiSelectModule } from 'primeng/primeng';
import {ScheduleModule, DialogModule, CalendarModule, ToggleButtonModule, DragDropModule, 
ButtonModule, InputTextareaModule, CheckboxModule, InputTextModule, SelectButtonModule} from 'primeng/primeng';
import { AddvacationComponent } from './addvacation/addvacation.component';
import { PlannedComponent } from './planned/planned.component';
import { UnplannedComponent } from './unplanned/unplanned.component';
import { DisablevacationComponent } from './disablevacation/disablevacation.component';

@NgModule({
  bootstrap: [],
  declarations: [ PlannedComponent,AddvacationComponent, UnplannedComponent, DisablevacationComponent],
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
   ReactiveFormsModule,
   MessagesModule,DragDropModule, ButtonModule, InputTextareaModule, CheckboxModule, InputTextModule, SelectButtonModule
  ],
  providers: [Configuration],
   schemas: [CUSTOM_ELEMENTS_SCHEMA],
})

export class VacationModule { }