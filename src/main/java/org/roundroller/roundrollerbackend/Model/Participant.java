package org.roundroller.roundrollerbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_sequence")
    @SequenceGenerator(name = "participant_sequence",sequenceName = "participant_sequence", allocationSize = 1)
    private Long participantId;

    private String participantName;

    private boolean Selected;
}
