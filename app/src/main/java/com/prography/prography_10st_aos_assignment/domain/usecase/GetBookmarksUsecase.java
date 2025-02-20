package com.prography.prography_10st_aos_assignment.domain.usecase;

import com.prography.prography_10st_aos_assignment.domain.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.domain.repository.LocalRepository;

import java.util.List;

public class GetBookmarksUsecase {
    private LocalRepository repository;

    public GetBookmarksUsecase(LocalRepository repository) {
        this.repository = repository;
    }

    public List<Bookmark> execute() {
        return repository.getBookmarks();
    }
}
