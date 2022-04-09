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
import { routing } from './report.routing';
import { DatePipe } from '@angular/common';
import { TimereportComponent } from "app/report/timereport/timereport.component";
import { NotReportedEmployeeComponent } from "app/report/not-reported-employee/not-reported-employee.component";
import { ClarificationComponent } from './clarification/clarification.component';
import { TickethistorystatusComponent } from './tickethistorystatus/tickethistorystatus.component';
import { DailystatusreportComponent } from "app/report/dailystatusreport/dailystatusreport.component";
import { NochangeComponent } from "app/report/nochange/nochange.component";
import { TicketstatusComponent } from "app/report/ticketstatus/ticketstatus.component";
import { TicketofthedayComponent } from './ticketoftheday/ticketoftheday.component';
import { DocumentationComponent } from './documentation/documentation.component';
import { ReportsummaryComponent } from './reportsummary/reportsummary.component';
import { UpdatestatusComponent } from "app/report/updatestatus/updatestatus.component";
import { NotreportedtimeComponent } from "app/report/notreportedtime/notreportedtime.component";


@NgModule({
  bootstrap: [],
  declarations: [NotReportedEmployeeComponent, NotreportedtimeComponent,TimereportComponent, ClarificationComponent, TickethistorystatusComponent,DailystatusreportComponent,NochangeComponent,TicketstatusComponent, TicketofthedayComponent, DocumentationComponent, ReportsummaryComponent,UpdatestatusComponent  ],
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

export class ReportModule { }