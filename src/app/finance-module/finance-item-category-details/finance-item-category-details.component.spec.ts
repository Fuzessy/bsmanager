import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceItemCategoryDetailsComponent } from './finance-item-category-details.component';

describe('FinanceItemCategoryDetailsComponent', () => {
  let component: FinanceItemCategoryDetailsComponent;
  let fixture: ComponentFixture<FinanceItemCategoryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinanceItemCategoryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceItemCategoryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
