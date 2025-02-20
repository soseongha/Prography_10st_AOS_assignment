package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

public class PhotoDetailViewModel extends ViewModel {
    private final GetPhotoUsecase getPhotoUsecase;
    private final MutableLiveData<Photo> photoLiveData = new MutableLiveData<>();
    private final String TAG = getClass().toString();

    public PhotoDetailViewModel(GetPhotoUsecase getPhotoUsecase) {
        this.getPhotoUsecase = getPhotoUsecase;
    }

    public MutableLiveData<Photo> getPhoto() {
        return photoLiveData;
    }

    public void setPhoto(Photo photo){
        photoLiveData.setValue(photo);
    }

    public void fetchPhoto(String id) {
        getPhotoUsecase.execute(id, new Callback<Photo>() {
            @Override
            public void onResponse(Photo result) {
                setPhoto(result);
            }
            @Override
            public void onFailure(Throwable result) {
                setPhoto(null);
            }
        });
    }
}
