import {Component} from '@angular/core';
import {ParticipantEntryComponent} from './features/participant-entry/participant-entry.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [ParticipantEntryComponent, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RoundRoller-FE';
}
