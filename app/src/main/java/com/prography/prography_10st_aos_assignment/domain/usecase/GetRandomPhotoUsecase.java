package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.UnsplashRepository;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class GetRandomPhotoUsecase {
    private final UnsplashRepository repository;

    public GetRandomPhotoUsecase(UnsplashRepository repository) {
        this.repository = repository;
    }

    public void execute(Callback<Photo> callback) {
        repository.getRandomPhoto( new Callback<Photo>() {
            @Override
            public void onResponse(Photo result) {
                callback.onResponse(result);
            }
            @Override
            public void onFailure(Throwable result) {
                callback.onFailure(result);
            }
        });

    }
}
