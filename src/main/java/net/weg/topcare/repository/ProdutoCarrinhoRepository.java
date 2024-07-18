package net.weg.topcare.repository;

import net.weg.topcare.entity.Produto;
import net.weg.topcare.entity.ProdutoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCarrinhoRepository extends JpaRepository<ProdutoCarrinho, Long> {
}
