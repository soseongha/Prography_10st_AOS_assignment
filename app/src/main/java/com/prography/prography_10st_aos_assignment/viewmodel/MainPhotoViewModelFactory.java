package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;

public class MainPhotoViewModelFactory implements ViewModelProvider.Factory {
    private final GetPhotosUsecase getPhotosUsecase;

    public MainPhotoViewModelFactory(GetPhotosUsecase getPhotosUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainPhotoViewModel.class)) {
            return (T) new MainPhotoViewModel(getPhotosUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}