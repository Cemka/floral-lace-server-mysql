package ru.myitschool.florallace.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public Product insert(String name,
                          String description,
                          Integer price,
                          Integer countLast,
                          Integer countStart,
                          String color,
                          String photoUrl) {

        /* делаем так, чтобы нельзя было добавить товар с одним и тем же названием много раз(> 1)
        * Иными словами предотвращаем создание копий
        */
        if(productRepository.findByName(name) != null){
            throw new RuntimeException("This product is already there: " + name);
        }
        return productRepository.save(Product
                .builder()
                .name(name)
                .description(description)
                .price(price)
                .countLast(countLast)
                .countStart(countStart)
                .color(color)
                .photoUrl(photoUrl)
                .build());
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        // проверяем существует ли товар
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @Override
    public Product getByName(String name) {
        // проверяем существует ли товар
        Product product = productRepository.findByName(name);
        if (product == null) {
            throw new RuntimeException("Product not found by name: " + name);
        }
        return product;
    }

    @Override
    public List<Product> findAllByColor(String color) {
        // проверяем существует ли цвет
        List<Product> productList = productRepository.findAllByColor(color);
        if(productList == null){
            throw new RuntimeException("This color isn't present: " + color);
        }
        return productList;
    }

    @Override
    public Product update(Long id,
                          String name,
                          String description,
                          Integer price,
                          Integer countLast,
                          Integer countStart,
                          String color,
                          String photoUrl) {

        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Product not found: " + id);
        }
        Product product = Product
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .countLast(countLast)
                .countStart(countStart)
                .color(color)
                .photoUrl(photoUrl)
                .build();
        return productRepository.save(product);
    }


    @Override
    public void deleteById(Long id) {
        // проверяем существует ли товар
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Product not found by id: " + id);
        }
        productRepository.deleteById(id);
    }
}
