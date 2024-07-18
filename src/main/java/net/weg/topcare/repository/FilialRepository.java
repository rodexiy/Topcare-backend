package net.weg.topcare.repository;

import net.weg.topcare.entity.Endereco;
import net.weg.topcare.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
}
