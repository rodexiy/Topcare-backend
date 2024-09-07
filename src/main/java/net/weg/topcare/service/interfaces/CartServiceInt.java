package net.weg.topcare.service.interfaces;

import net.weg.topcare.entity.ProductCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartServiceInt {
    List<ProductCart> getAll(Long idClient);
    Double getCartTotalPriceDiscounted(Long idClient);
    Double getCartTotalPriceNotDiscounted(Long idClient);
    Double getCartTotalDiscount(Long idClient);

}
