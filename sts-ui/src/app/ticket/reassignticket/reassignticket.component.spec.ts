import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReassignticketComponent } from './reassignticket.component';

describe('ReassignticketComponent', () => {
  let component: ReassignticketComponent;
  let fixture: ComponentFixture<ReassignticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReassignticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReassignticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
