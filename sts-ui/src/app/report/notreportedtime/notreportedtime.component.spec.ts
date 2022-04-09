import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotreportedtimeComponent } from './notreportedtime.component';

describe('NotreportedtimeComponent', () => {
  let component: NotreportedtimeComponent;
  let fixture: ComponentFixture<NotreportedtimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotreportedtimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotreportedtimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
