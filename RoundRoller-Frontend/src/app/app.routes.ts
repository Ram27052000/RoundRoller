import { Routes } from '@angular/router';
import {DiceRollerComponent} from './features/dice-roller/dice-roller.component';
import {ParticipantEntryComponent} from './features/participant-entry/participant-entry.component';

export const routes: Routes = [
  {path: '', component: ParticipantEntryComponent },
  {path: 'dice-roller', component: DiceRollerComponent}
];
