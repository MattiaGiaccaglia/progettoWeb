import { TestBed } from '@angular/core/testing';

import { FidelitycardService } from './fidelitycard.service';

describe('FidelitycardService', () => {
  let service: FidelitycardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FidelitycardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
