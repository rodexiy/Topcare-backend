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

    private String contentType;
    private String originalFileName;
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Product product;

    public Image(MultipartFile file) throws IOException {
        this.bytes = file.getBytes();
        this.contentType = "data:image/" + file.getContentType() + ";base64," + file.getOriginalFilename();
        this.originalFileName = file.getOriginalFilename();

    }

}

