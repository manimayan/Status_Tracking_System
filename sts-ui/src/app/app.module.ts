import { NgModule, ApplicationRef, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MultiSelectModule,DataTableModule } from 'primeng/primeng';
import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { Configuration } from './app.constants';
import {DatepickerModule} from 'ngx-date-picker';
import {CalendarModule} from 'primeng/primeng';
import { DropdownModule } from "primeng/primeng";      
import { MessagesModule } from 'primeng/primeng';
import { HomeComponent } from './home/home.component';
import { TicketComponent } from './ticket/ticket.component';
import { ReportComponent } from "app/report/report.component";
import { TesterComponent } from './tester/tester.component';
import { TesterhomeComponent } from './tester/testerhome/testerhome.component';
import {ChartModule} from "primeng/primeng";
import { DashboardComponent } from './dashboard/dashboard.component';
import { SecurityComponent } from "app/employee/security/security.component";





@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    HomeComponent,
    TicketComponent,
    ReportComponent,
    TesterComponent,
    DashboardComponent,
  
  
  ],
  imports: [
    BrowserAnimationsModule,
    HttpModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    DropdownModule,
    routing,
    CommonModule,
    routing,
    MultiSelectModule,
    DataTableModule,
    DatepickerModule,
    MessagesModule,
    ChartModule,
  ],
  providers: [Configuration],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})

export class AppModule { }