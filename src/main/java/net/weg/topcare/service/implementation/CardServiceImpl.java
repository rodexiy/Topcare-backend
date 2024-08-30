package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.entity.Card;
import net.weg.topcare.entity.Client;
import net.weg.topcare.exceptions.CardNotFoundException;
import net.weg.topcare.repository.CardRepository;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.service.interfaces.CardServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardServiceInt {
    private final CardRepository repository;
    private final ClientServiceImpl clientService;
    @Override
    public CardGetRequestDTO addCard(CardPostRequestDTO dto) {
        ClientGetDTO clientDTO = clientService.findOne(dto.idClient());
        Client client = new Client(clientDTO.id());
        List<Card> cards = repository.findAllByClient_Id(dto.idClient());

        Card card = Card.builder().cardName(dto.cardName())
                .numbers(dto.numbers()).expiration(dto.expiration())
                .client(client).build();

        if(cards.isEmpty()){
            card.setStandard(true);
        }

        repository.save(card);
        return card.toGetDTO();
    }

    @Override
    public List<CardGetRequestDTO> getAllCardByClientId(Long id) {
        return repository.findAllByClient_Id(id).stream().map(Card::toGetDTO).toList();
    }

    @Override
    public List<CardGetRequestDTO> patchCardStandard(Long id, Long cardId) throws CardNotFoundException {
//        Card card = repository.findById(cardId).orElseThrow(() -> new CardNotFoundException());
          List<Card> card = repository.findAllByClient_Id(id);
          if(card.isEmpty()){
              throw new CardNotFoundException();
          }
          for(Card c : card){
              if(c.getId().equals(cardId)){
                  if(c.isStandard()){
                      c.setStandard(false);
                  }else{
                      for(Card card1 : card){
                          card1.setStandard(false);
                          repository.save(card1);
                      }
                      c.setStandard(true);
                  }
                  repository.save(c);
              }
          }
          return card.stream().map(Card::toGetDTO).toList();
    }

    @Override
    public boolean deleteCard(Long id, Long cardId) {
        List<Card> card = repository.findAllByClient_Id(id);

        for(Card c : card){
            if(c.getId().equals(cardId)){
                repository.deleteById(cardId);
                return true;
            }
        }

        return false;
    }
}
