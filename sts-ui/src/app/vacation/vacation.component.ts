import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ScheduleModule} from 'primeng/primeng';
import { NavbarService } from "app/navbar.service";

@Component({
  selector: 'vacation',
  encapsulation: ViewEncapsulation.None,
  template: ``,
  styleUrls: [""],
  providers: []
})

export class VacationComponent implements OnInit {

  constructor(public nav: NavbarService) {
  }

  ngOnInit() {
      this.nav.show();
  }
}
