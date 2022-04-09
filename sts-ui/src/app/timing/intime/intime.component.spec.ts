import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IntimeComponent } from './intime.component';

describe('IntimeComponent', () => {
  let component: IntimeComponent;
  let fixture: ComponentFixture<IntimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IntimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IntimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
