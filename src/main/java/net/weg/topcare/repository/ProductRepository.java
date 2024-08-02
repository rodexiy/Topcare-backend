package net.weg.topcare.repository;

import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContainingIgnoreCase(String searchTerm, Pageable pageRequest);
    List<Product> getTopByRatings(List<Rating> ratings);
}
