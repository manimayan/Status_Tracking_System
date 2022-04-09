import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {ScheduleModule} from 'primeng/primeng';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import * as $ from 'jquery';
import 'fullcalendar';
window["$"] = $;
window["jQuery"] = $;
import {PlannedVacationService} from './planned.service';
import { Vacation } from "app/model/vacation";
import { NavbarService } from "app/navbar.service";

@Component({
  selector: 'app-planned',
  templateUrl: './planned.component.html',
  styleUrls: ['./planned.component.css',],
  providers:[PlannedVacationService]
  })

export class PlannedComponent implements OnInit {   
events: any[];
headerConfig: any;
plannedEvents :any[]=[];
private vaclist : Vacation[] = [];

  constructor(public service:PlannedVacationService,public nav: NavbarService) { }

  ngOnInit() { this.getPlannedVacationDetails();this.nav.show();
  this.headerConfig = {
          left: 'prev,next ,today',
          center: 'title',
          right: 'month,agendaWeek,'
      };}

  convert(str) {
      var date = new Date(str),
          mnth = ("0" + (date.getMonth()+1)).slice(-2),
          day  = ("0" + date.getDate()).slice(-2);
      return [  date.getFullYear(),mnth, day ].join("-");
      }
  
  change(addday) {
      var date = new Date(addday),
          mnth = ("0" + (date.getMonth()+1)).slice(-2),
          day  = ("0" + (date.getDate()+1)).slice(-2);
      return [  date.getFullYear(),mnth, day ].join("-");
      }
  

  getPlannedVacationDetails() {
    this.service.getPlannedVacationDetails().subscribe(
            value => {    
                this.vaclist=value;
                value.forEach(item=>
              {
                
               var vacationDateFrom = this.convert(item.vacationDateFrom);
               var vacationDateTo = this.change(item.vacationDateTo);
              this.plannedEvents.push({ 'title' : item.employee.employeeName, 'start' : vacationDateFrom , 'end': vacationDateTo  });
            
         })
             this.events = this.plannedEvents;
        }
             )};
  }    
  
