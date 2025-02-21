package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;

public class RandomPhotoViewModelFactory implements ViewModelProvider.Factory {
    private final GetRandomPhotoUsecase getRandomPhotoUsecase;
    private final ToggleBookmarkUsecase toggleBookmarkUsecase;

    public RandomPhotoViewModelFactory(GetRandomPhotoUsecase getRandomPhotoUsecase, ToggleBookmarkUsecase toggleBookmarkUsecase) {
        this.getRandomPhotoUsecase = getRandomPhotoUsecase;
        this.toggleBookmarkUsecase = toggleBookmarkUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RandomPhotoViewModel.class)) {
            return (T) new RandomPhotoViewModel(getRandomPhotoUsecase, toggleBookmarkUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}