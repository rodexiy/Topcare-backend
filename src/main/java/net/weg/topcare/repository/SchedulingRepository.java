package net.weg.topcare.repository;

import net.weg.topcare.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    List<Scheduling> findByScheduledDateAfter(LocalDateTime date);


    List<Scheduling> findByClientId(Long clientId);


    List<Scheduling> findByClientIdAndScheduledDateAfter(Long id, LocalDateTime now);


    Scheduling findBySchedulingNumber(String schedulingNumber);
}
