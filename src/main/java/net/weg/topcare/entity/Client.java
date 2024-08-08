package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
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
    @JsonIgnore
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "client") // cascade = CascadeType.DETACH
    private List<Pet> pets = new ArrayList<>();

    @OneToOne
    private Image banner;

    @OneToOne
    private Image profilePicture;

    public Client(ClientPostDTO dto) {
        BeanUtils.copyProperties(dto, this);

        Cart cart = new Cart();
        cart.setClient(this);
        this.setCart(cart);
    }

    public Client(Long id){
        this.setId(id);
    }
    public ClientGetDTO toGetDTO() {;
        return new ClientGetDTO(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getCpf(),
                this.getPhone(),
                this.getMainAddress(),
                this.getAddress(),
                this.getCart(),
                this.getProductsFavorite(),
                this.getSchedules(),
                this.getPets(),
                this.getBanner(),
                this.getProfilePicture(),
                this.getBirthdate());
    }

    /**
     * Adiciona um produto à lista de produtos favoritos do cliente.
     *
     * @param produto - Produto a ser adicionado.
     */
    public void addProductFavorite(Product produto) {
        if (!productsFavorite.contains(produto)) {
            productsFavorite.add(produto);
        }
    }

    /**
     * Remove um produto à lista de produtos favoritos do cliente.
     *
     * @param produto - Produto a ser adicionado.
     */
    public void removeProductFavorite(Product produto) {
            productsFavorite.remove(produto);
    }

    /**
     * Remove um produto à lista de pedidos do cliente.
     *
     * @param cartOrder - Pedido a ser adicionado.
     */
    public void addCartOrderToOrders(CartOrder cartOrder) {
        orders.add(cartOrder);
    }

    public void addQuery(Scheduling scheduling) {
        schedules.add(scheduling);
    }
}
