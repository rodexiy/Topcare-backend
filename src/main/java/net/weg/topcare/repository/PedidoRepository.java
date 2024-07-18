package net.weg.topcare.repository;

import net.weg.topcare.entity.Pagamento;
import net.weg.topcare.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
