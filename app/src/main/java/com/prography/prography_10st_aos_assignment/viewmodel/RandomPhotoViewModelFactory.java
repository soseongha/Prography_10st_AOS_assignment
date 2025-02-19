package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;

public class RandomPhotoViewModelFactory implements ViewModelProvider.Factory {
    private final GetRandomPhotoUsecase getRandomPhotoUsecase;

    public RandomPhotoViewModelFactory(GetRandomPhotoUsecase getRandomPhotoUsecase) {
        this.getRandomPhotoUsecase = getRandomPhotoUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RandomPhotoViewModel.class)) {
            return (T) new RandomPhotoViewModel(getRandomPhotoUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}