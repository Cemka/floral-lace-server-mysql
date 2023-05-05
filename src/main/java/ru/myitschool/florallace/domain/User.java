package ru.myitschool.florallace.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "phone_numb")
    private String phoneNumb;

    @Column(name = "count_of_bonus")
    private int countOfBonus;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Cart.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = FavouriteList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "favourite_list_id")
    private FavouriteList favouriteList;

}
