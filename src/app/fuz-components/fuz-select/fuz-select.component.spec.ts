import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuzSelectComponent } from './fuz-select.component';

describe('FuzSelectComponent', () => {
  let component: FuzSelectComponent;
  let fixture: ComponentFixture<FuzSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuzSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuzSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
