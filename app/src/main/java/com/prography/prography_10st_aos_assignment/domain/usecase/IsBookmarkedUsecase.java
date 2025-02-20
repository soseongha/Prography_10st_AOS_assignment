package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.LocalRepository;

public class IsBookmarkedUsecase {
    private LocalRepository repository;

    public IsBookmarkedUsecase(LocalRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String photoId) {
        return repository.isBookmarked(photoId);
    }
}
