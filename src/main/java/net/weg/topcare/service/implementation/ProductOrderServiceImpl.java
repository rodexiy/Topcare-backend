package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.entity.ProductOrder;
import net.weg.topcare.repository.ProductOrderRepository;
import net.weg.topcare.service.interfaces.ProductOrderServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderServiceInt {

    private final ProductOrderRepository repository;

    @Override
    public List<ProductOrder> getAllByProductOrder() {
        return repository.findAllByOrderByProduct();
    }
}
