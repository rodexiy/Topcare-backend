package net.weg.topcare.repository;

import net.weg.topcare.entity.CartOrder;
import net.weg.topcare.entity.ProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
    Page<CartOrder> findAllByClient_Id(Long clientId, Pageable pageable);
    List<CartOrder> findAllByClient_Id(Long clientId);
}
