package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.IsBookmarkedUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public class RandomPhotoViewModel extends ViewModel {
    private final GetRandomPhotoUsecase getRandomPhotoUsecase;
    private final ToggleBookmarkUsecase toggleBookmarkUsecase;
    private final MutableLiveData<Photo> randomPhotoLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> toggleBookmarkLiveData = new MutableLiveData<>();
    private final String TAG = getClass().toString();

    public RandomPhotoViewModel(GetRandomPhotoUsecase getRandomPhotoUsecase, ToggleBookmarkUsecase toggleBookmarkUsecase) {
        this.getRandomPhotoUsecase = getRandomPhotoUsecase;
        this.toggleBookmarkUsecase = toggleBookmarkUsecase;
    }

    public MutableLiveData<Photo> getRandomPhoto() {
        return randomPhotoLiveData;
    }

    public MutableLiveData<Boolean> getToggleBookmark() {
        return toggleBookmarkLiveData;
    }

    public void setRandomPhoto(Photo randomPhoto){
        randomPhotoLiveData.setValue(randomPhoto);
    }

    public void setToggleBookmark(Boolean toggleBookmark){
        toggleBookmarkLiveData.postValue(toggleBookmark);
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

    public void toggleBookmark(Photo photo, Boolean isBookmarked){
        new Thread(() -> {
            boolean bookmarked = toggleBookmarkUsecase.execute(photo, isBookmarked);
            setToggleBookmark(bookmarked);
        }).start();
    }
}
