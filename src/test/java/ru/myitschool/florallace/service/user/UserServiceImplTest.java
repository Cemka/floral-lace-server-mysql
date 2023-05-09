package ru.myitschool.florallace.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.service.order.OrderServiceImpl;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.myitschool.florallace.service.user.Constants.*;

@DisplayName("UserServiceImplTest")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    private OrderServiceImpl userService;

    @Test
    @DisplayName("Should insert user")
    void shouldInsertUser() {

        User expectedUser = User
                .builder()
                .id(4L)
                .phoneNumb(PHONE_NUMB)
                .firstName(FIRST_NAME)
                .secondName(SECOND_NAME)
                .countOfBonus(COUNT_OF_BONUS)
                .favouriteProducts(FAVOURITE_LIST)
                .build();
        // fixme изменить логику теста через, написать его через мокито
    }
}