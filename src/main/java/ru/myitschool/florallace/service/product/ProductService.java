package ru.myitschool.florallace.service.product;

import ru.myitschool.florallace.domain.Product;

import java.util.List;


public interface ProductService {

    Product insert(Product product);

    List<Product> getAll();

    Product getById(Long id);

    Product getByName(String name);

    Product update(Product product);

    void deleteById(long id);
}
