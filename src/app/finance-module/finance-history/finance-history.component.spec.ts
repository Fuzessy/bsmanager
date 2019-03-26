import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceHistoryComponent } from './finance-history.component';

describe('FinanceHistoryComponent', () => {
  let component: FinanceHistoryComponent;
  let fixture: ComponentFixture<FinanceHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinanceHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
