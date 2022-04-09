import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'ticket',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './ticket.component.html',
  styleUrls: ["./ticket.component.css"],
  providers: []
})
export class TicketComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
