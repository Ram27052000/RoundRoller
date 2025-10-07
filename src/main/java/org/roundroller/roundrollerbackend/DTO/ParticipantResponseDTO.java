package org.roundroller.roundrollerbackend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantResponseDTO {

    private List<Long> ids;

    private int count;

    private List<String> names;
}
