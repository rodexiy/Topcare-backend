package net.weg.topcare.repository;

import net.weg.topcare.entity.Categoria;
import net.weg.topcare.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
