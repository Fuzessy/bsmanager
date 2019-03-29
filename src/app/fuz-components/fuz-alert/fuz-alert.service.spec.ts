import { TestBed } from '@angular/core/testing';

import { FuzAlertService } from './fuz-alert.service';

describe('FuzAlertService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FuzAlertService = TestBed.get(FuzAlertService);
    expect(service).toBeTruthy();
  });
});
