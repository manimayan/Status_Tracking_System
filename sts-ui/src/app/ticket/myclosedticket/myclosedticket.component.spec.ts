import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyclosedticketComponent } from './myclosedticket.component';

describe('MyclosedticketComponent', () => {
  let component: MyclosedticketComponent;
  let fixture: ComponentFixture<MyclosedticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyclosedticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyclosedticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
