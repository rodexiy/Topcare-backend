package net.weg.topcare.repository;

import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Boolean existsByEmail(String email);

    Boolean existsClientByCpf(String cpf);
}
