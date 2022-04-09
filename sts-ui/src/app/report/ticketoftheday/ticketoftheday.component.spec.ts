import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketofthedayComponent } from './ticketoftheday.component';

describe('TicketofthedayComponent', () => {
  let component: TicketofthedayComponent;
  let fixture: ComponentFixture<TicketofthedayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketofthedayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketofthedayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
