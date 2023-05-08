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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "count_last")
    private Integer count_last;

    @Column(name = "count_start")
    private Integer count_start;

    @Column(name = "color")
    private String color;
}
