package net.weg.topcare.repository;

import net.weg.topcare.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> getRatingsByProduct_Id(Long product_id);

}
