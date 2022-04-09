import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignticketComponent } from './assignticket.component';

describe('AssignticketComponent', () => {
  let component: AssignticketComponent;
  let fixture: ComponentFixture<AssignticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
