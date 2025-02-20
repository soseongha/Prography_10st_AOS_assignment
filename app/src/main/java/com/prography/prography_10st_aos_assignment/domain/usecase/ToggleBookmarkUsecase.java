package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.LocalRepository;

public class ToggleBookmarkUsecase {
    private LocalRepository repository;

    public ToggleBookmarkUsecase(LocalRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Photo photo, Boolean isBookmarked) {
        boolean newsState;
        if (isBookmarked) {
            repository.removeBookmark(photo);
            newsState = false;
        } else {
            repository.addBookmark(photo);
            newsState = true;
        }
        return newsState;
    }
}
