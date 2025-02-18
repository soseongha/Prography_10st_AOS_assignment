package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;

public class PhotoViewModelFactory implements ViewModelProvider.Factory {
    private final GetPhotosUsecase getPhotosUsecase;

    public PhotoViewModelFactory(GetPhotosUsecase getPhotosUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PhotoViewModel.class)) {
            return (T) new PhotoViewModel(getPhotosUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}