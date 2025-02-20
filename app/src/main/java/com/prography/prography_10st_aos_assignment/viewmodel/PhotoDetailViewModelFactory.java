package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;

public class PhotoDetailViewModelFactory implements ViewModelProvider.Factory {
    private final GetPhotoUsecase getPhotoUsecase;

    public PhotoDetailViewModelFactory(GetPhotoUsecase getPhotoUsecase) {
        this.getPhotoUsecase = getPhotoUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel.class)) {
            return (T) new PhotoDetailViewModel(getPhotoUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}