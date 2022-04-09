import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TickethistorystatusComponent } from './tickethistorystatus.component';

describe('TickethistorystatusComponent', () => {
  let component: TickethistorystatusComponent;
  let fixture: ComponentFixture<TickethistorystatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TickethistorystatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TickethistorystatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
