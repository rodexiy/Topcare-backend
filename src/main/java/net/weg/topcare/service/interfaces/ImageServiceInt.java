package net.weg.topcare.service.interfaces;

import net.weg.topcare.entity.Image;

import java.util.List;

public interface ImageServiceInt {
    List<Image> getAllImagesByProductId(Long productId);
}
