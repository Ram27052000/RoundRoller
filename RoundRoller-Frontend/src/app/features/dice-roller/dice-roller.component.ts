import { Component } from '@angular/core';
import {ParticipantDisplayComponent} from './participant-display/participant-display.component';

@Component({
  selector: 'app-dice-roller',
  imports: [
    ParticipantDisplayComponent
  ],
  templateUrl: './dice-roller.component.html',
  styleUrl: './dice-roller.component.css'
})
export class DiceRollerComponent {

}
