import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddvacationComponent } from './addvacation.component';

describe('AddvacationComponent', () => {
  let component: AddvacationComponent;
  let fixture: ComponentFixture<AddvacationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddvacationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddvacationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
