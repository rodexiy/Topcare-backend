package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.rating.GeneralRatingPostDTO;
import net.weg.topcare.entity.Rating;
import net.weg.topcare.service.implementation.RatingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rating")
@CrossOrigin("*")
public class RatingController {

    private final RatingServiceImpl service;
    @PostMapping
    public ResponseEntity<Rating> postRating(@RequestBody GeneralRatingPostDTO dto){
        try {
            return new ResponseEntity<>(service.addRating(dto), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<>(service.getAllRatings(), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Rating> getRating(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getRating(id), HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
