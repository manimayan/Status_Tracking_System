import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DailystatusreportComponent } from './dailystatusreport.component';

describe('DailystatusreportComponent', () => {
  let component: DailystatusreportComponent;
  let fixture: ComponentFixture<DailystatusreportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DailystatusreportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DailystatusreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
