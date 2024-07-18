package net.weg.topcare.repository;

import net.weg.topcare.entity.Pet;
import net.weg.topcare.entity.PetAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetAgendamentoRepository extends JpaRepository<PetAgendamento, Long> {
}
