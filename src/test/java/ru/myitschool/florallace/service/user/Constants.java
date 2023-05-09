package ru.myitschool.florallace.service.user;

import ru.myitschool.florallace.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    protected static final String PHONE_NUMB = "79225551234";
    protected static final String FIRST_NAME = "Иван";
    protected static final String SECOND_NAME = "Сидоров";
    protected static final Integer COUNT_OF_BONUS = 823;
    private static  final Product PRODUCT1 = Product
            .builder()
            .id(4L)
            .name("Пион")
            .description("Тестовое описание")
            .price(1234)
            .countLast(1234)
            .countStart(1234)
            .color("Красный")
            .photoUrl("photoUrl")
            .build();
    private static final Product PRODUCT2 = Product
            .builder()
            .id(4L)
            .name("Пион")
            .description("Тестовое описание")
            .price(1234)
            .countLast(1234)
            .countStart(1234)
            .color("Красный")
            .photoUrl("photoUrl")
            .build();

    protected static final List<Product> FAVOURITE_LIST = new ArrayList<>(Arrays.asList(PRODUCT1, PRODUCT2));

    private static final String NAME_PRODUCT1 = "Пион";
}
