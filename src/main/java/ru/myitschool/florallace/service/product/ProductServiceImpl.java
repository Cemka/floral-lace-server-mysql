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

    @NonNull
    private ProductRepository repository;
    @Override
    public Product add(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
