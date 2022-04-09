import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { TimereportComponent } from "app/report/timereport/timereport.component";
import { ClarificationComponent } from "app/report/clarification/clarification.component";
import { TickethistorystatusComponent } from "app/report/tickethistorystatus/tickethistorystatus.component";
import { NotReportedEmployeeComponent } from "app/report/not-reported-employee/not-reported-employee.component";
import { DailystatusreportComponent } from "app/report/dailystatusreport/dailystatusreport.component";
import { NochangeComponent } from "app/report/nochange/nochange.component";
import { TicketstatusComponent } from "app/report/ticketstatus/ticketstatus.component";
import { TicketofthedayComponent } from "app/report/ticketoftheday/ticketoftheday.component";
import { DocumentationComponent } from "app/report/documentation/documentation.component";
import { ReportsummaryComponent } from "app/report/reportsummary/reportsummary.component";
import { UpdatestatusComponent } from "app/report/updatestatus/updatestatus.component";
import { NotreportedtimeComponent } from "app/report/notreportedtime/notreportedtime.component";


export const routes: Routes = [{path:'',children:[
                               { path: 'timereport', component:  TimereportComponent},
                               { path: 'nottimereport', component:  NotreportedtimeComponent},
                               { path: 'clarification', component:  ClarificationComponent},
                               { path: 'tickethistory', component:  TickethistorystatusComponent},
                               { path: 'notreported', component:  NotReportedEmployeeComponent},
                               { path:'dailystatus',component: DailystatusreportComponent},
                               { path:'ticketstatus',component: TicketstatusComponent},
                               { path:'nochange',component: NochangeComponent},
                               { path:'ticketoftheday',component: TicketofthedayComponent},
                               {path:'document', component:DocumentationComponent},
                               {path:'reportsummary', component:ReportsummaryComponent},
                               {path:'updatestatus', component:UpdatestatusComponent}
                               ]}
                               ];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);