package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.myitschool.florallace.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update Product p set p.photoUrl = :photoUrl where p.name = :name")
    void updatePhotoByName(@Param("name") String name,
                      @Param("photoUrl") String photoUrl);

    @Modifying
    @Query("update Product p set p.color = :color where p.name = :name")
    void updateColorByName(@Param("name") String name,
                           @Param("color") String color);
    List<Product> findAllByColor(String color);
}
