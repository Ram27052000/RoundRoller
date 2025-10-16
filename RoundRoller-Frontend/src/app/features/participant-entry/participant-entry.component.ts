import { Component } from '@angular/core';
import {ParticipantCountFormComponent} from './participant-count-form/participant-count-form.component';
import {ParticipantNameFormComponent} from './participant-name-form/participant-name-form.component';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-participant-entry',
  imports: [
    ParticipantCountFormComponent,
    ParticipantNameFormComponent,
    NgIf,
  ],
  templateUrl: './participant-entry.component.html',
  styleUrl: './participant-entry.component.css'
})
export class ParticipantEntryComponent {
  participantCount!: number;

  receiveCount($event: number) {
      console.log('number count from parent', $event);
      this.participantCount = $event;
  }
}
