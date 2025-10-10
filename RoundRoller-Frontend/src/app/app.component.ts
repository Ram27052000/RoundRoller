import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {
  ParticipantCountFormComponent
} from './features/participant-entry/participant-count-form/participant-count-form.component';

@Component({
  selector: 'app-root',
  imports: [ParticipantCountFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RoundRoller-FE';
}
