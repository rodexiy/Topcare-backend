package net.weg.topcare.repository;


import lombok.NonNull;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByBrand_Id(@NonNull Long brand_id);
    List<Product> findAllByOrderByRatingsDesc();

    @Query("SELECT p FROM Product p "
            + "WHERE p.name LIKE %:query% "
            + "OR p.id IN ("
            + "  SELECT pp.id FROM Product pp "
            + "  JOIN pp.categories c "
            + "  WHERE c IN (:categories) "
            + "  GROUP BY pp.id "
            + "  HAVING COUNT(DISTINCT c) <= :categoriesSize"
            + ")")
    Page<Product> findProductsByCategoriesOrNameLike(@Param("query") String query, @Param("categories") List<Category> categories, @Param("categoriesSize") int categoriesSize, Pageable pageable);

    @Query("SELECT p FROM Product p "
            + "JOIN p.categories c "
            + "WHERE c IN (:categories) "
            + "GROUP BY p.id "
            + "HAVING COUNT(DISTINCT c) <= :categoriesSize")
    Page<Product> findProductsByCategories(@Param("categories") List<Category> categories,
                                           @Param("categoriesSize") int categoriesSize,
                                           Pageable pageable);

    @Query("SELECT p FROM Product p "
            + "JOIN p.categories c "
            + "WHERE c IN (:categories) "
            + "GROUP BY p.id "
            + "HAVING COUNT(DISTINCT c) = :categoriesSize")
    List<Product> findProductsByCategories(@Param("categories") List<Category> categories,
                                           @Param("categoriesSize") int categoriesSize);
}
