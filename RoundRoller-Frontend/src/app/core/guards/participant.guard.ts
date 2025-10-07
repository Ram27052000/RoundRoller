import { CanActivateFn } from '@angular/router';

export const participantGuard: CanActivateFn = (route, state) => {
  return true;
};
