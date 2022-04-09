import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnplannedComponent } from './unplanned.component';

describe('VacationDetailsComponent', () => {
  let component: UnplannedComponent;
  let fixture: ComponentFixture<UnplannedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnplannedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnplannedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
