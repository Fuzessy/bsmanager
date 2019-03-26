import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuzNumberComponent } from './fuz-number.component';

describe('FuzNumberComponent', () => {
  let component: FuzNumberComponent;
  let fixture: ComponentFixture<FuzNumberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuzNumberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuzNumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
