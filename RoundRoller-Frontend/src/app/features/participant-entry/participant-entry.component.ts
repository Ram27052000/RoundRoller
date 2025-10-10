import { Component } from '@angular/core';
import {ParticipantCountFormComponent} from './participant-count-form/participant-count-form.component';

@Component({
  selector: 'app-participant-entry',
  imports: [
    ParticipantCountFormComponent
  ],
  templateUrl: './participant-entry.component.html',
  styleUrl: './participant-entry.component.css'
})
export class ParticipantEntryComponent {

  receiveCount($event: number) {
      console.log('number count from parent', $event);
  }
}
