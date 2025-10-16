package org.roundroller.roundrollerbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RollResponseDTO {

    private Long participantId;

    private String name;

    private int remainingParticipantCount;

    private boolean cycleComplete;

}
