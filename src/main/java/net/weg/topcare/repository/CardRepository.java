package net.weg.topcare.repository;

import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByClient_Id(Long id);
    Optional<Card> findCardByClient_Id(Long id);
}
