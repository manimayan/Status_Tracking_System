import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetpasswordhomeComponent } from './resetpasswordhome.component';

describe('ResetpasswordComponent', () => {
  let component: ResetpasswordhomeComponent;
  let fixture: ComponentFixture<ResetpasswordhomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResetpasswordhomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetpasswordhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
