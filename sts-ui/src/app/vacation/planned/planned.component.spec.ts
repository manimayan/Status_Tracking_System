import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannedComponent } from './planned.component';

describe('VacationDetailsComponent', () => {
  let component: PlannedComponent;
  let fixture: ComponentFixture<PlannedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
