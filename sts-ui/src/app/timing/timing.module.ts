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
import { routing } from './timing.routing';
import { OuttimeComponent } from './outtime/outtime.component';
import { DatePipe } from '@angular/common';
//import { NavserviceService } from "app/navservice.service";
import { IntimeComponent } from './intime/intime.component';

@NgModule({
  bootstrap: [],
  declarations: [  
OuttimeComponent, IntimeComponent],
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
  providers: [Configuration, DatePipe]
})

export class TimingModule { }