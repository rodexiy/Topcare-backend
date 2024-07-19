package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.client.ClienteGetDTO;
import net.weg.topcare.controller.dto.client.ClientePostDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Client extends People {
    @OneToOne
    private Address mainAddress;

    @OneToMany
    private List<Address> address = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<CartOrder> orders = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Scheduling> schedules = new ArrayList<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Cart cart;

    @OneToMany(mappedBy = "client") // cascade = CascadeType.REFRESH
    private List<Payment> payments = new ArrayList<>();

    @ManyToMany
    private List<Product> productsFavorite = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "client") // cascade = CascadeType.DETACH
    private List<Pet> pets = new ArrayList<>();

    @OneToOne
    private Image banner;

    @OneToOne
    private Image profilePicture;

    public Client(ClientePostDTO dto) {
        BeanUtils.copyProperties(dto, this);

        Cart cart = new Cart();
        cart.setClient(this);
        this.setCart(cart);


    }

    public ClienteGetDTO toGetDTO() {;
        return new ClienteGetDTO(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getCpf(),
                this.getPhone(),
                this.getMainAddress(),
                this.getAddress(),
                this.getCart(),
                this.getProductsFavorite(),
                this.getPets(),
                this.getBanner(),
                this.getProfilePicture(),
                this.getBirthdate());
    }
}
