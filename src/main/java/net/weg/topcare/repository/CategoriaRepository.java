package net.weg.topcare.repository;

import net.weg.topcare.entity.Cartao;
import net.weg.topcare.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
