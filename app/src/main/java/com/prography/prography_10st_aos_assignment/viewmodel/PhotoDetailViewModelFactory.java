package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.domain.usecase.IsBookmarkedUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;

public class PhotoDetailViewModelFactory implements ViewModelProvider.Factory {
    private final GetPhotoUsecase getPhotoUsecase;
    private final IsBookmarkedUsecase isBookmarkedUsecase;
    private final ToggleBookmarkUsecase bookMarkUsecase;

    public PhotoDetailViewModelFactory(GetPhotoUsecase getPhotoUsecase, IsBookmarkedUsecase isBookmarkedUsecase, ToggleBookmarkUsecase bookMarkUsecase) {
        this.getPhotoUsecase = getPhotoUsecase;
        this.isBookmarkedUsecase = isBookmarkedUsecase;
        this.bookMarkUsecase = bookMarkUsecase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel.class)) {
            return (T) new PhotoDetailViewModel(getPhotoUsecase, isBookmarkedUsecase, bookMarkUsecase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}