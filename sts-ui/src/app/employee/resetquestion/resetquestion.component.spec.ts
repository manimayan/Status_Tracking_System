import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetquestionComponent } from './resetquestion.component';

describe('ResetquestionComponent', () => {
  let component: ResetquestionComponent;
  let fixture: ComponentFixture<ResetquestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResetquestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetquestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
