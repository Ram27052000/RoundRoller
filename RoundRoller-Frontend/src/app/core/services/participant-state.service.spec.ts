import { TestBed } from '@angular/core/testing';

import { ParticipantStateService } from './participant-state.service';

describe('ParticipantStateService', () => {
  let service: ParticipantStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticipantStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
