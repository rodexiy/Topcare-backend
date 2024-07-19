package net.weg.topcare.repository;

import net.weg.topcare.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Scheduling, Long> {
}
