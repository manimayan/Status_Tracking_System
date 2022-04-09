import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {ScheduleModule} from 'primeng/primeng';
import {UnplannedVacationService} from './unplanned.service';
import * as $ from 'jquery';
import 'fullcalendar';
import { NavbarService } from "app/navbar.service";
import { Vacation } from "app/model/vacation";
window["$"] = $;
window["jQuery"] = $;   

@Component({
  selector: 'app-unplanned',
  templateUrl: './unplanned.component.html',
  styleUrls: ['./unplanned.component.css'],
  providers:[UnplannedVacationService]
})

export class UnplannedComponent implements OnInit { 
    events: any[];
    header: any;
    headerConfig: any;
    UnplannedEvents :any[]=[];
private vaclist : Vacation[] = [];

      constructor(public service:UnplannedVacationService,public nav: NavbarService) { }

      ngOnInit() { this.getUnPlannedVacationDetails(); this.nav.show();
     
      this.headerConfig = {
              left: 'prev,next today',
              center: 'title',
              right: 'month,agendaWeek,'              
          }; 
          } 
      
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
      
      getUnPlannedVacationDetails() {
          
        this.service.getUnPlannedVacationDetails().subscribe(
            res => {
              this.vaclist=res;
                
                  res.forEach(item=>
                  { 
                      console.log("fgdfgdfgdfgdf"+item.employee);
                      var vacationDateFrom = this.convert(item.vacationDateFrom);
                      var vacationDateTo = this.change(item.vacationDateTo);
                
                   this.UnplannedEvents.push({'title' : item.employee.employeeName, 'start' : vacationDateFrom , 'end': vacationDateTo });
                  })
                    this.events = this.UnplannedEvents;
                  });
      }    
      }