package net.weg.topcare.repository;

import net.weg.topcare.entity.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
}
