import { FuzComponentsModule } from './fuz-components.module';

describe('FuzComponentsModule', () => {
  let fuzComponentsModule: FuzComponentsModule;

  beforeEach(() => {
    fuzComponentsModule = new FuzComponentsModule();
  });

  it('should create an instance', () => {
    expect(fuzComponentsModule).toBeTruthy();
  });
});
