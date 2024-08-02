package net.weg.topcare.repository;

import net.weg.topcare.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    /**
     * Retorna os agendamentos com data agendada posterior à data informada.
     *
     * @param date Data de referência.
     * @return Lista de agendamentos.
     */
    List<Scheduling> findByScheduledDateAfter(LocalDateTime date);
}
