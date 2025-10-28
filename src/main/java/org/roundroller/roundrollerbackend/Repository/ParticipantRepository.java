package org.roundroller.roundrollerbackend.Repository;

import jakarta.transaction.Transactional;
import org.roundroller.roundrollerbackend.Model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query(value = "SELECT * FROM Participant p WHERE selected = false ORDER BY RANDOM () LIMIT 1 ",
            nativeQuery = true)
    Participant findRandomUnselectedId();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Participant p SET p.selected = false ")
    void resetAllParticipants();

    int countBySelectedFalse();
}
