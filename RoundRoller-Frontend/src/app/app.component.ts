import {Component} from '@angular/core';
import {ParticipantEntryComponent} from './features/participant-entry/participant-entry.component';

@Component({
  selector: 'app-root',
  imports: [ParticipantEntryComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RoundRoller-FE';
}
