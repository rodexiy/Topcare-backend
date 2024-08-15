package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import net.weg.topcare.exceptions.CardNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CardServiceInt {
    CardPostRequestDTO addCard(CardPostRequestDTO dto);
    List<CardGetRequestDTO> getAllCardByClientId(Long id);
    boolean patchCardStandard(Long id, Long cardId) throws CardNotFoundException;
    boolean deleteCard(Long id, Long cardId);



}
