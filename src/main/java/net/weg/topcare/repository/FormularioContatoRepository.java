package net.weg.topcare.repository;

import net.weg.topcare.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioContatoRepository extends JpaRepository<ContactForm, Long> {
}
