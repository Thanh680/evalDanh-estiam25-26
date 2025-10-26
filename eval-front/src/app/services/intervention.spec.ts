import { InterventionService } from './intervention';
import { TestBed } from '@angular/core/testing';

describe('Intervention', () => {
  let service: InterventionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterventionService);
  });

  it('should create an instance', () => {
    expect(service).toBeTruthy();
  });
});
