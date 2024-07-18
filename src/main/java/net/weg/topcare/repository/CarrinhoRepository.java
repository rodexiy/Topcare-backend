package net.weg.topcare.repository;

import net.weg.topcare.entity.Avaliacao;
import net.weg.topcare.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
