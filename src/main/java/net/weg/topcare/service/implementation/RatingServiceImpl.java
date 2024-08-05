package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductPatchRatingDTO;
import net.weg.topcare.controller.dto.rating.GeneralRatingPostDTO;
import net.weg.topcare.controller.dto.rating.RatingGetDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.Rating;
import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.repository.RatingRepository;
import net.weg.topcare.service.interfaces.RatingServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingServiceInt {

    private final RatingRepository repository;
    private final ProductServiceImpl productService;

    @Override
    public Rating addRating(GeneralRatingPostDTO dto) throws ProductNotFoundException {
        Rating rating = new Rating(dto);
        Client client = new Client(dto.client().id());
        rating.setClient(client);
        Product product = new Product(dto.product().id());
        List<Rating> ratings = getAllRatingsByProductId(dto.product().id());
        if (ratings.isEmpty()) {
            ratings.add(rating);
        }
        product.setRatings(ratings);
        rating.setProduct(product);
        Integer total = 0;
        for (Rating rating1 : ratings){
            total += rating1.getRating();
        }
        product.setGeneralRating(total / ratings.size());
        productService.putProductRating(dto.product().id(), new ProductPatchRatingDTO(product.getGeneralRating()));
        return repository.save(rating);
    }

    @Override
    public Rating getRating(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Rating não encontrada!"));
    }

    @Override
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByProductId(Long product_id) {
        return repository.getRatingsByProduct_Id(product_id);
    }
}
