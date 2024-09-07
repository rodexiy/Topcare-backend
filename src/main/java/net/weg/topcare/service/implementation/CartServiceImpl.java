package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.ProductCart;
import net.weg.topcare.repository.CartRepository;
import net.weg.topcare.service.interfaces.CartServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartServiceInt {
    private final CartRepository repository;
    private final ClientServiceImpl clientService;
    @Override
    public List<ProductCart> getAll(Long idClient) {
        Client client = clientService.findOneClient(idClient);
        return client.getCart().getProductsInCart();
    }

    @Override
    public Double getCartTotalPriceDiscounted(Long idClient) {
        Client client = clientService.findOneClient(idClient);
        return client.getCart().getCartTotalDiscounted();
    }

    @Override
    public Double getCartTotalPriceNotDiscounted(Long idClient) {
        Client client = clientService.findOneClient(idClient);
        return client.getCart().getCartTotalNotDiscounted();
    }

    @Override
    public Double getCartTotalDiscount(Long idClient) {
        Client client = clientService.findOneClient(idClient);
        return client.getCart().getCartTotalDiscountAmount();
    }
}
