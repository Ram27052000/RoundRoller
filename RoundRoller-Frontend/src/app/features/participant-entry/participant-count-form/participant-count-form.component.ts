import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
@Component({
  selector: 'app-participant-count-form',
  imports: [MatFormField, MatLabel, MatInput, ReactiveFormsModule, MatButton],
  templateUrl: './participant-count-form.component.html',
  styleUrl: './participant-count-form.component.css'
})

export class ParticipantCountFormComponent {

  countForm: FormGroup;
  @Output() countOfParticipants = new EventEmitter<number>();
  constructor(private fb: FormBuilder) {
    this.countForm = this.fb.group({
      count: [0, [Validators.min(2), Validators.required, Validators.max(20)]]
    });
  }

  countPeople() {
    if(this.countForm.valid){
      const countValue = this.countForm.get('count')?.value;
      console.log(`count value ${countValue}`);
      this.countOfParticipants.emit(countValue);

    }
  }
}
