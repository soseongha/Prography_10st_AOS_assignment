package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class RandomPhotoViewModel extends ViewModel {
    private final GetRandomPhotoUsecase getRandomPhotoUsecase;
    private final MutableLiveData<Photo> randomPhotoLiveData = new MutableLiveData<>();
    private final String TAG = getClass().toString();

    public RandomPhotoViewModel(GetRandomPhotoUsecase getRandomPhotoUsecase) {
        this.getRandomPhotoUsecase = getRandomPhotoUsecase;
    }

    public MutableLiveData<Photo> getRandomPhoto() {
        return randomPhotoLiveData;
    }

    public void setRandomPhoto(Photo randomPhoto){
        randomPhotoLiveData.setValue(randomPhoto);
    }

    public void fetchRandomPhoto() {
        getRandomPhotoUsecase.execute(new Callback<Photo>() {
            @Override
            public void onResponse(Photo result) {
                setRandomPhoto(result);
            }
            @Override
            public void onFailure(Throwable result) {
                setRandomPhoto(null);
            }
        });
    }
}
