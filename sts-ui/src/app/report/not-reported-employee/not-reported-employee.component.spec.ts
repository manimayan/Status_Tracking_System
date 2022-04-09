import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotReportedEmployeeComponent } from './not-reported-employee.component';

describe('NotReportedEmployeeComponent', () => {
  let component: NotReportedEmployeeComponent;
  let fixture: ComponentFixture<NotReportedEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotReportedEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotReportedEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
