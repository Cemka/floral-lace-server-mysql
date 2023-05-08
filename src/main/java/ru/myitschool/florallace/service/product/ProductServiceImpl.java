package ru.myitschool.florallace.service.product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
