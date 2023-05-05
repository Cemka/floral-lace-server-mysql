package ru.myitschool.florallace.service.favouritelist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.FavouriteList;
import ru.myitschool.florallace.repository.FavouriteListRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteListServiceImpl implements FavouriteListService{

    private FavouriteListRepository repository;

    @Override
    public FavouriteList add(FavouriteList favouriteList) {
        return repository.save(favouriteList);
    }

    @Override
    public FavouriteList getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<FavouriteList> getAll() {
        return repository.findAll();
    }

    @Override
    public FavouriteList update(FavouriteList favouriteList) {
        return repository.save(favouriteList);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
