package org.roundroller.roundrollerbackend.Service;

import org.roundroller.roundrollerbackend.DTO.ParticipantRequestDTO;
import org.roundroller.roundrollerbackend.DTO.ParticipantResponseDTO;
import org.roundroller.roundrollerbackend.Model.Participant;
import org.roundroller.roundrollerbackend.Repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Transactional
    public ParticipantResponseDTO addParticipant
            (ParticipantRequestDTO participantRequestDTO){
        List<Participant> participants = new ArrayList<>();
        for(String name : participantRequestDTO.getNames()){
            Participant participant = new Participant();
            participant.setParticipantName(name);
            participant.setSelected(false); //default value would be false but for clear clarity we are setting it here
            participants.add(participant);
        }
        List<Participant> savedParticipant  = participantRepository.saveAll(participants);

        ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
        participantResponseDTO.setNames(savedParticipant
                .stream()
                    .map(participant -> participant.getParticipantName()).toList());
        participantResponseDTO.setCount(savedParticipant.size());

        return participantResponseDTO;
    }
}
