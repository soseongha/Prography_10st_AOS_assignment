package com.prography.prography_10st_aos_assignment.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetBookmarksUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.viewmodel.MainPhotoViewModel;

public class MainPhotoViewModelFactory implements ViewModelProvider.Factory {
    private final GetPhotosUsecase getPhotosUsecase;
    private final GetBookmarksUsecase getBookmarksUsecase;

    public MainPhotoViewModelFactory(GetPhotosUsecase getPhotosUsecase, GetBookmarksUsecase getBookmarksUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
        this.getBookmarksUsecase = getBookmarksUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainPhotoViewModel.class)) {
            return (T) new MainPhotoViewModel(getPhotosUsecase, getBookmarksUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}