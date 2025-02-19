package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class MainPhotoViewModel extends ViewModel {
    private final GetPhotosUsecase getPhotosUsecase;
    private final MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();
    private int page;
    private int perPage;
    private final String TAG = getClass().toString();

    public MainPhotoViewModel(GetPhotosUsecase getPhotosUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
        page = 1;
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
                page++;
            }
            @Override
            public void onFailure(Throwable result) {
                setPhotos(null);
            }
        });
    }

    public void clearPage(){
        page = 1;
    }
}
