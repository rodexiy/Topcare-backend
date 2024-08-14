package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CardServiceInt {
    CardPostRequestDTO addCard(CardPostRequestDTO dto);
    List<CardGetRequestDTO> getAllCard(Long id);
    boolean patchCardStandard(Long id, Long cardId);
    boolean deleteCard(Long id, Long cardId);



}
