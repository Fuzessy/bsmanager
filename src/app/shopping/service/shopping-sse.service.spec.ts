import { TestBed } from '@angular/core/testing';

import { ShoppingSseService } from './shopping-sse.service';

describe('ShoppingSseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShoppingSseService = TestBed.get(ShoppingSseService);
    expect(service).toBeTruthy();
  });
});
