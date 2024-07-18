package net.weg.topcare.repository;

import net.weg.topcare.entity.Marca;
import net.weg.topcare.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
