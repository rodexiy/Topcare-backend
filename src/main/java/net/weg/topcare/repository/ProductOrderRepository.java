package net.weg.topcare.repository;

import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> getTopByProduct_IdOrderByProductDesc(Long id);
    List<ProductOrder> findAllByOrderByProductDesc();
}
