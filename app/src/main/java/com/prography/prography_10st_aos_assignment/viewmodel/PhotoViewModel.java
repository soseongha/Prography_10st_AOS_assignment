package com.prography.prography_10st_aos_assignment.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class PhotoViewModel extends ViewModel {
    private final GetPhotosUsecase getPhotosUsecase;
    private final MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();
    private int page;
    private int perPage;

    public PhotoViewModel(GetPhotosUsecase getPhotosUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
        page = 0;
        perPage = 10;
    }

    public LiveData<List<Photo>> getPhotos() {
        return photosLiveData;
    }

    public void setPhotos(List<Photo> photos){
        photosLiveData.setValue(photos);
    }

    public void fetchPhotos() {

        getPhotosUsecase.execute(page, perPage, new Callback<List<Photo>>() {
            @Override
            public void onResponse(List<Photo> result) {
                setPhotos(result);
            }

            @Override
            public void onFailure(Throwable result) {
                setPhotos(null);
            }
        });
    }
}
