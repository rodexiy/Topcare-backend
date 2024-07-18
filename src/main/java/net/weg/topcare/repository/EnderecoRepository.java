package net.weg.topcare.repository;

import net.weg.topcare.entity.Cliente;
import net.weg.topcare.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
