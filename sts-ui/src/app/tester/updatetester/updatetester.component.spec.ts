import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatetesterComponent } from './updatetester.component';

describe('UpdatetesterComponent', () => {
  let component: UpdatetesterComponent;
  let fixture: ComponentFixture<UpdatetesterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatetesterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatetesterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
