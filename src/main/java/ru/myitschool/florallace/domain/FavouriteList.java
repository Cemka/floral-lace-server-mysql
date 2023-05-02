package ru.myitschool.florallace.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "favourite_list")
public class FavouriteList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourite_list_product",
            joinColumns = @JoinColumn(name = "favourite_list_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
