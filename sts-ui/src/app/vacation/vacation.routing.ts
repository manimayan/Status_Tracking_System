import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { PlannedComponent } from './planned/planned.component';
import { UnplannedComponent } from './unplanned/unplanned.component';
import { AddvacationComponent } from './addvacation/addvacation.component';
import { DisablevacationComponent } from "app/vacation/disablevacation/disablevacation.component";


export const routes: Routes = [
                               {
                                   path: '',
                                   children: [
                                              { path: '', component:  AddvacationComponent},
                                              { path: 'plannedVacation', component:  PlannedComponent},
                                              { path: 'unplannedVacation', component:  UnplannedComponent},
                                              { path: 'disable', component: DisablevacationComponent},
                                              ]}
                                          ];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);