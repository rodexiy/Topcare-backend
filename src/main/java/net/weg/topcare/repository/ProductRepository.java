package net.weg.topcare.repository;

import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
=======
import net.weg.topcare.entity.Rating;
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
//    Page<ProductMinimalGetDTO> search(String searchTerm, Pageable pageable);

    List<Product> findAllByNameContainingIgnoreCase(String searchTerm, PageRequest pageRequest);
<<<<<<< HEAD
=======

>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
}
