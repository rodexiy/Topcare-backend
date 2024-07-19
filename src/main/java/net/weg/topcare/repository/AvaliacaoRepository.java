package net.weg.topcare.repository;

import net.weg.topcare.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Rating, Long> {
}
