import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuzAlertComponent } from './fuz-alert.component';

describe('FuzAlertComponent', () => {
  let component: FuzAlertComponent;
  let fixture: ComponentFixture<FuzAlertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuzAlertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuzAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
