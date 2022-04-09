import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { TesterhomeComponent } from "app/tester/testerhome/testerhome.component";
import { UpdatetesterComponent } from "app/tester/updatetester/updatetester.component";




export const routes: Routes = [{path:'',
                               children:[
                                          { path : '' , component : TesterhomeComponent },
                                          {path:'updatetester', component:UpdatetesterComponent},
                                          {path :'testerhome' , component : TesterhomeComponent},
                                         ]}
                                         ];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);