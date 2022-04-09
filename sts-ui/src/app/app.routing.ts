import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from "app/home/home.component";
import { DashboardComponent } from "app/dashboard/dashboard.component";

export const routes: Routes = [
                               { path: '', redirectTo: 'employee', pathMatch: 'full' },
                               { path: 'employee', loadChildren: './employee/employee.module#EmployeeModule' },
                               { path: 'vacation', loadChildren: './vacation/vacation.module#VacationModule' },
                               { path: 'ticket',   loadChildren: './ticket/ticket.module#TicketModule' },
                               
                               { path: 'timing',   loadChildren: './timing/timing.module#TimingModule' },
                               { path: 'report',   loadChildren: './report/report.module#ReportModule' },
                               { path: 'tester',   loadChildren: './tester/tester.module#TesterModule' },
                               { path: 'home',     component : HomeComponent},
                               { path: 'dashboard',component : DashboardComponent}
                             ];
export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });
