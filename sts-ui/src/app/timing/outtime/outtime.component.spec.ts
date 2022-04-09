import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OuttimeComponent } from './outtime.component';

describe('OuttimeComponent', () => {
  let component: OuttimeComponent;
  let fixture: ComponentFixture<OuttimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OuttimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OuttimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
