package ru.myitschool.florallace.service.product;

import ru.myitschool.florallace.domain.Product;

import java.util.List;


public interface ProductService {

    Product insert(String name,
                   String description,
                   Integer price,
                   Integer countLast,
                   Integer countStart,
                   String color,
                   String photoUrl
    );

    List<Product> getAll();

    Product getById(Long id);

    Product getByName(String name);

    List<Product> findAllByColor(String color);


    Product update(Long id,
                   String name,
                   String description,
                   Integer price,
                   Integer countLast,
                   Integer countStart,
                   String color,
                   String photoUrl
    );

    void deleteById(Long id);
}
