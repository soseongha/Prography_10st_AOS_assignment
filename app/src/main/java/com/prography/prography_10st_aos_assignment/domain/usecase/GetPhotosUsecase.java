package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.UnsplashRepository;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class GetPhotosUsecase {
    private final UnsplashRepository repository;

    public GetPhotosUsecase(UnsplashRepository repository) {
        this.repository = repository;
    }

    public void execute(int page, int perPage, Callback<List<Photo>> callback) {
        repository.getPhotos(page, perPage, new Callback<List<Photo>>() {
            @Override
            public void onResponse(List<Photo> result) {
                callback.onResponse(result);
            }
            @Override
            public void onFailure(Throwable result) {
                callback.onFailure(result);
            }
        });

    }
}
