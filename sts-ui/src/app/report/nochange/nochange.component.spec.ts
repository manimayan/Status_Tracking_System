import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NochangeComponent } from './nochange.component';

describe('NochangeComponent', () => {
  let component: NochangeComponent;
  let fixture: ComponentFixture<NochangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NochangeComponent ]
    })
    
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NochangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
