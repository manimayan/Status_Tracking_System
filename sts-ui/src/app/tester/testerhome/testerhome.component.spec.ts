import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterhomeComponent } from './testerhome.component';

describe('TesterhomeComponent', () => {
  let component: TesterhomeComponent;
  let fixture: ComponentFixture<TesterhomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TesterhomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
