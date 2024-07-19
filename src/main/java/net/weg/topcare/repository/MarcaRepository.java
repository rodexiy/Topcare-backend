package net.weg.topcare.repository;

import net.weg.topcare.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Brand, Long> {
}
