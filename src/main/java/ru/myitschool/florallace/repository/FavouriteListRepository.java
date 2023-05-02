package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.FavouriteList;

public interface FavouriteListRepository extends JpaRepository<FavouriteList, Long> {
}
