package net.weg.topcare.repository;

import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    List<Scheduling> findByScheduledDateAfter(LocalDateTime date);


    List<Scheduling> findByClientId(Long clientId);


    List<Scheduling> findByClientIdAndScheduledDateAfter(Long id, LocalDateTime now);


    Scheduling findBySchedulingNumber(String schedulingNumber);

    @Query("SELECT s FROM Scheduling sch JOIN sch.pets p JOIN p.servicesSelected s WHERE p.id = :petId")
    List<Service> findServicesByPetId(@Param("petId") Long petId);

}
