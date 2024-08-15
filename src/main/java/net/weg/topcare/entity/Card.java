package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Client client;

    @Column(nullable = false, length = 100)
    private String cardName;

    @Column(nullable = false, length = 19)
    private String numbers;

    @Column(nullable = false)
    private LocalDate expiration;
    @Column(nullable = false)
    private boolean standard;

    public CardPostRequestDTO toPostDto(){
        return new CardPostRequestDTO(
                this.cardName,
                this.numbers,
                this.expiration,
                this.client.getId());
    }
    public CardGetRequestDTO toGetDTO(){
        return new CardGetRequestDTO(
                this.cardName,
                this.numbers,
                this.expiration,
                this.standard,
                this.id);
    }
}
