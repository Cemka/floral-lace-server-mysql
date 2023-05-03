package ru.myitschool.florallace.service.product;

import ru.myitschool.florallace.domain.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);

    Product getById(long id);

    Product update(Product product);

    List<Product> getAll();

    void deleteById(long id);
}
