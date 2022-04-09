import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseticketComponent } from './releaseticket.component';

describe('ReleaseticketComponent', () => {
  let component: ReleaseticketComponent;
  let fixture: ComponentFixture<ReleaseticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReleaseticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReleaseticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
