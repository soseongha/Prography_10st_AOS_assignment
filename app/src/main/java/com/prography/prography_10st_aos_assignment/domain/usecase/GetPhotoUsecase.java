package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.UnsplashRepository;
import com.prography.prography_10st_aos_assignment.utils.Callback;

public class GetPhotoUsecase {
    private final UnsplashRepository repository;

    public GetPhotoUsecase(UnsplashRepository repository) {
        this.repository = repository;
    }

    public void execute(String id, Callback<Photo> callback) {
        repository.getPhoto(id, new Callback<Photo>() {
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
