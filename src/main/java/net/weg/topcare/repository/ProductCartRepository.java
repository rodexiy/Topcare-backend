package net.weg.topcare.repository;

import net.weg.topcare.entity.Cart;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    void deleteByProduct_Id(Long id);

    ProductCart findByProductAndCart(Product product, Cart cart);
}
