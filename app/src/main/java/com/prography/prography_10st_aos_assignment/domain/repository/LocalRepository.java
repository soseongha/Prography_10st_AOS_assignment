package com.prography.prography_10st_aos_assignment.domain.repository;

import com.prography.prography_10st_aos_assignment.domain.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.List;

public interface LocalRepository {
    public boolean isBookmarked(String photoId);
    public List<Bookmark> getBookmarks();
    public void addBookmark(Photo photo);
    public void removeBookmark(Photo photo);
}
