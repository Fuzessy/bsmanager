import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceItemCategoriesComponent } from './finance-item-categories.component';

describe('FinanceItemCategoriesComponent', () => {
  let component: FinanceItemCategoriesComponent;
  let fixture: ComponentFixture<FinanceItemCategoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinanceItemCategoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceItemCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
