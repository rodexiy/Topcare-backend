package net.weg.topcare.repository;

import net.weg.topcare.entity.Filial;
import net.weg.topcare.entity.FormularioContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioContatoRepository extends JpaRepository<FormularioContato, Long> {
}
