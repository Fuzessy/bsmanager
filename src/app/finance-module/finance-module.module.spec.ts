import { FinanceModuleModule } from './finance-module.module';

describe('FinanceModuleModule', () => {
  let financeModuleModule: FinanceModuleModule;

  beforeEach(() => {
    financeModuleModule = new FinanceModuleModule();
  });

  it('should create an instance', () => {
    expect(financeModuleModule).toBeTruthy();
  });
});
