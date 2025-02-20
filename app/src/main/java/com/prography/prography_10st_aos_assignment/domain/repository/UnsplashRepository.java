package com.prography.prography_10st_aos_assignment.domain.repository;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public interface UnsplashRepository {
    void getPhotos(int page, int perPage, Callback<List<Photo>> callback);
    void getRandomPhoto(Callback<Photo> callback);
    void getPhoto(String id, Callback<Photo> callback);
}
