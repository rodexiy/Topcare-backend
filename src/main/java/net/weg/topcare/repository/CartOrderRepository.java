package net.weg.topcare.repository;

import net.weg.topcare.entity.CartOrder;
import net.weg.topcare.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
}
