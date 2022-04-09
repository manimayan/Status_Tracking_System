import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from "app/home/home.component";




export const routes: Routes = [{path:'',
                               children:[
                                         {path:'home',component:HomeComponent},
                                         ]}
                                         ];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);