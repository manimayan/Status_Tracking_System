import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditpriorityComponent } from './editpriority.component';

describe('EditpriorityComponent', () => {
  let component: EditpriorityComponent;
  let fixture: ComponentFixture<EditpriorityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditpriorityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditpriorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
