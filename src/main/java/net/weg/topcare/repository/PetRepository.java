package net.weg.topcare.repository;

import net.weg.topcare.entity.Pedido;
import net.weg.topcare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
