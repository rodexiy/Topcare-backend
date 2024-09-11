package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "mediumblob", nullable = false)
    @Lob
    private byte[] bytes;

    @OneToOne(mappedBy = "profilePicture")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Client clientProfilePicture;

    @OneToOne(mappedBy = "banner")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Client clientBanner;

    private String contentType;
    private String originalFileName;
    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @OneToOne(mappedBy = "picture")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn()
    private Pet pet;

    @OneToOne(mappedBy = "image")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Brand brand;

    @OneToOne(mappedBy = "image")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private ProductCart productCart;




    public Image(MultipartFile file) throws IOException {
        this.bytes = file.getBytes();
        this.contentType = file.getContentType();
        this.originalFileName = file.getOriginalFilename();

    }

}

