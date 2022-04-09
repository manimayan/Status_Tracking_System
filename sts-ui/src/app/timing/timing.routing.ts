import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { OuttimeComponent } from './outtime/outtime.component';
import { IntimeComponent } from './intime/intime.component';

export const routes: Routes = [{path:'',children:[
                               { path: 'intime', component:  IntimeComponent},
                               { path: 'outtime', component:  OuttimeComponent},
                               ]}
                               ];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);