package net.weg.topcare.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.entity.Image;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.service.interfaces.ImageServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageServiceInt {
    private final ImageRepository repository;

    @Override
    public List<Image> getAllImagesByProductId(Long productId) {
        return repository.getAllByProduct_Id(productId);
    }

    @Override
    public Boolean deleteImageById(Long imageId) {
        repository.deleteById(imageId);
        return true;
    }
}
