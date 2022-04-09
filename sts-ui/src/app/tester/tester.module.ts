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
import { routing } from './tester.routing';
import { TesterhomeComponent } from './testerhome/testerhome.component';

import { UpdatetesterComponent } from './updatetester/updatetester.component';


@NgModule({
  bootstrap: [],
  declarations: [TesterhomeComponent,  UpdatetesterComponent],
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

export class TesterModule { }