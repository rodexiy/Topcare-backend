package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.service.interfaces.CloneProductInt;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    private Integer generalRating;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSpecification> specifications = new ArrayList<>();


    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Image> images = new ArrayList<>();

    @Column
    private Integer discount = 0;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;

    public Double getDiscountedPrice() {
        return this.price - this.getDiscountedAmount();
    }

    public Double getDiscountedAmount() {
        return ((this.price * discount) / 100);
    }


    public Product(ProductPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public ProductGetDTO toGetDTO(){
        return new ProductGetDTO(
                this.id,
                this.brand,
                this.name,
                this.price,
                this.categories,
                new GeneralRatingGetDTO(this.generalRating, (long) this.ratings.size()),
                this.discount,
                this.description,
                this.specifications.stream().map(ProductSpecification::toGetDTO).toList(),
                this.images,
                this.stock
        );
    }
    public Product(Product product){
//        this.id = product.id;
//        this.brand = product.brand;
//        this.name = product.name;
//        this.description = product.description;
//        this.generalRating = product.generalRating;
//        this.categories = product.categories;
//        this.specifications = product.specifications;
//        this.images = product.images;
//        this.discount = product.discount;
//        this.price = product.price;
//        this.stock = product.stock;
//        this.ratings = product.ratings;
        BeanUtils.copyProperties(product, this);
    }

    public ProductMinimalGetDTO toMinimalGetDTO(){
        return new ProductMinimalGetDTO(
            this.id,
            this.name,
            this.price,
            this.discount,
            this.images.get(0),
            new GeneralRatingGetDTO(this.generalRating, (long) this.ratings.size()),
                this.categories.stream().map(Category::getName).toList()
        );
    }
}
