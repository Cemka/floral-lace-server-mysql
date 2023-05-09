package ru.myitschool.florallace.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_numb")
    private String phoneNumb;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "count_of_bonus")
    private Integer countOfBonus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "favourite_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favouriteProducts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsInCart;

    @OneToOne(mappedBy = "userId")
    private Order userOrder;



    // todo изменить логику чтобы нельзя было много раз нажать на отправку данных(клиентская часть)
}
