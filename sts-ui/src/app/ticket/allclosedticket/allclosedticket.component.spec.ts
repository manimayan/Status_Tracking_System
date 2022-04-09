import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllclosedticketComponent } from './allclosedticket.component';

describe('AllclosedticketComponent', () => {
  let component: AllclosedticketComponent;
  let fixture: ComponentFixture<AllclosedticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllclosedticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllclosedticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
