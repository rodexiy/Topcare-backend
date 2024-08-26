package net.weg.topcare.repository;

import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameContainingIgnoreCase(String name);
}
