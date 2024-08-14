package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import net.weg.topcare.entity.Card;
import net.weg.topcare.entity.Client;
import net.weg.topcare.repository.CardRepository;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.service.interfaces.CardServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardServiceInt {
    private final CardRepository repository;
    private final ClientRepository clientRepository;
    @Override
    public CardPostRequestDTO addCard(CardPostRequestDTO dto) {
        Client client = clientRepository.findById(dto.idClient()).get();
        System.out.println(client);
        Card card = Card.builder().cardName(dto.cardName())
                .numbers(dto.numbers()).expiration(dto.expiration())
                .client(client).build();
        System.out.println(card);
        repository.save(card);
        return card.toPostDto();
    }

    @Override
    public List<CardGetRequestDTO> getAllCard(Long id) {
        return null;
    }

    @Override
    public boolean patchCardStandard(Long id, Long cardId) {
        return false;
    }

    @Override
    public boolean deleteCard(Long id, Long cardId) {
        return false;
    }
}
