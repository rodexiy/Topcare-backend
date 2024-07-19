package net.weg.topcare.repository;

import net.weg.topcare.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Payment, Long> {
}
