package org.roundroller.roundrollerbackend.Service;

import org.roundroller.roundrollerbackend.DTO.ParticipantRequestDTO;
import org.roundroller.roundrollerbackend.DTO.ParticipantResponseDTO;
import org.roundroller.roundrollerbackend.DTO.RollResponseDTO;
import org.roundroller.roundrollerbackend.Model.Participant;
import org.roundroller.roundrollerbackend.Repository.participantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ParticipantService {

    private final participantRepository participantRepository;

    public ParticipantService(participantRepository participantRepository) {
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

    public ParticipantResponseDTO retrieveAllParticipants() {

        ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
        List<Participant> participant =  participantRepository.findAll();
        participantResponseDTO.setNames(participant.stream()
                .map(participant1 -> participant1.getParticipantName()).toList());
        participantResponseDTO.setCount(participant.size());
        participantResponseDTO.setIds(participant.stream().map(p -> p.getParticipantId()).toList());
        return participantResponseDTO;
    }

//    public RollResponseDTO rollDice(){
//        RollResponseDTO rollResponseDTO = new RollResponseDTO();
//        List<Participant> participants = participantRepository.findAll();
//        List<Long> participantIds = participants.stream()
//                .filter(participant -> !participant.isSelected())
//                    .map(p -> p.getParticipantId()).toList();
//        if(!participantIds.isEmpty()) {
//            Random random = new Random();
//            int randomIndex = random.nextInt(participantIds.size());
//            Long selectedId = participantIds.get(randomIndex);
//            Participant participant = participantRepository.findByParticipantId(selectedId);
//            participant.setSelected(true);
//            participantRepository.save(participant);
//            rollResponseDTO.setParticipantID(selectedId);
//            rollResponseDTO.setName(participant.getParticipantName());
//            rollResponseDTO.setRemainingParticipantCount(participantIds.size()-1);
//            rollResponseDTO.setCycleComplete(false);
//            return rollResponseDTO;
//        }
//        else{
//            for(Participant participant : participants){
//                    participant.setSelected(false);
//            }
//            participantRepository.saveAll(participants);
//        }
//        return rollDice();
//    }


 //Below is the optimized code -> Only few db calls , not major use of java streams , below code would be
//    fine even if the db grows


    public RollResponseDTO rollDice(){
        RollResponseDTO rollResponseDTO = new RollResponseDTO();
        Participant participant = participantRepository.findRandomUnselectedId();
        if(participant == null){
            participantRepository.resetAllParticipants();
            participant = participantRepository.findRandomUnselectedId();
            rollResponseDTO.setCycleComplete(true);
        }
        else{
            rollResponseDTO.setCycleComplete(false);
        }
        participant.setSelected(true);
        participantRepository.save(participant);
        rollResponseDTO.setName(participant.getParticipantName());
        rollResponseDTO.setParticipantID(participant.getParticipantId());
        List<Participant> participantsCount = participantRepository
                .findAll()
                    .stream()
                        .filter(p -> !p.isSelected()).toList();

        rollResponseDTO.setRemainingParticipantCount(participantsCount.size());
        return rollResponseDTO;
    }

}
