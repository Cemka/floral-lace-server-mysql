package ru.myitschool.florallace.service.favitem;

import ru.myitschool.florallace.domain.FavItem;

import java.util.List;

public interface FavItemService {

    FavItem insert(Long userId,
                   Long productId
    );

    List<FavItem> getAll();

    FavItem getById(Long id);

    FavItem update(Long id,
                    Long userId,
                    Long productId
    );

    FavItem getByUserAndProduct(Long userId,
                                 Long productId);

    void deleteById(Long id);
}
