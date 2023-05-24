package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.myitschool.florallace.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName, u.secondName = :secondName WHERE u.id = :id")
    void updateUserByIdAndName(@Param("id") Long id, @Param("firstName") String firstName, @Param("secondName") String secondName);

    @Modifying
    @Query("UPDATE User u SET u.phoneNumb = :phoneNumb WHERE u.id = :id")
    void updateUserByIdAndPhone(@Param("id") Long id, @Param("phoneNumb") String phoneNumb);

    @Modifying
    @Query("UPDATE User u SET u.countOfBonus = :countOfBonus WHERE u.id = :id")
    void updateUserByIdAndCountOfBonus(@Param("id") Long id, @Param("countOfBonus") Integer countOfBonus);
    User findByPhoneNumb(String name);

    User getByPhoneNumbAndPassword(String phoneNumb, String password);
}
