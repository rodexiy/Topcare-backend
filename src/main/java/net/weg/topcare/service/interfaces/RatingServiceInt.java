package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.controller.dto.rating.GeneralRatingPostDTO;
import net.weg.topcare.controller.dto.rating.RatingGetDTO;
import net.weg.topcare.entity.Rating;
import net.weg.topcare.exceptions.ProductNotFoundException;

import java.util.List;

public interface RatingServiceInt {
    Rating addRating(GeneralRatingPostDTO dto) throws ProductNotFoundException;
    Rating getRating(Long id);
    List<Rating> getAllRatings();
    List<Rating> getAllRatingsByProductId(Long product_id);

}
