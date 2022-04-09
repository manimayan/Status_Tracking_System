import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClarificationComponent } from './clarification.component';

describe('ClarificationComponent', () => {
  let component: ClarificationComponent;
  let fixture: ComponentFixture<ClarificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClarificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClarificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
