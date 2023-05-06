package ru.myitschool.florallace.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should delete user by id")
    void shouldDeleteUserById() {

        int sizeBeforeDelete = userRepository.findAll().size();

        userRepository.deleteById(2L);

        int sizeAfterDelete = userRepository.findAll().size();

        assertThat(sizeBeforeDelete).isEqualTo(sizeAfterDelete + 1);
    }
}