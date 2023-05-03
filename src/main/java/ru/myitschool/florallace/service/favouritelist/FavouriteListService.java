package ru.myitschool.florallace.service.favouritelist;

import ru.myitschool.florallace.domain.FavouriteList;

import java.util.List;

public interface FavouriteListService {

    FavouriteList add(FavouriteList favouriteList);

    FavouriteList getById(long id);

    List<FavouriteList> getAll();

    FavouriteList update(FavouriteList favouriteList);

    void deleteById(long id);
}
