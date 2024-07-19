package net.weg.topcare.repository;

import net.weg.topcare.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Card, Long> {
}
