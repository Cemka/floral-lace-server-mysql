package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myitschool.florallace.domain.FavouriteList;

public interface FavouriteListRepository extends JpaRepository<FavouriteList, Long> {
}
