package com.prography.prography_10st_aos_assignment.data.repositoryImpl;

import android.content.Context;

import com.prography.prography_10st_aos_assignment.data.local.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.data.local.dao.BookmarkDao;
import com.prography.prography_10st_aos_assignment.data.local.database.BookmarkDatabase;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.LocalRepository;

import java.util.ArrayList;
import java.util.List;

public class LocalRepositoryImpl implements LocalRepository {
    private BookmarkDao bookmarkDao;

    public LocalRepositoryImpl(Context context) {
        BookmarkDatabase db = BookmarkDatabase.getInstance(context);
        this.bookmarkDao = db.bookmarkDao();
    }

    @Override
    public boolean isBookmarked(String photoId) {
        return bookmarkDao.getBookmarkByPhotoId(photoId) != null;
    }

    @Override
    public List<com.prography.prography_10st_aos_assignment.domain.entity.Bookmark> getBookmarks() {
        List<com.prography.prography_10st_aos_assignment.domain.entity.Bookmark> domainBookmarks = new ArrayList<>();
        List<Bookmark> bookmarks = bookmarkDao.getBookmarksAll();
        if(bookmarks != null){
             for(Bookmark bookmark : bookmarks) {
                 domainBookmarks.add(new com.prography.prography_10st_aos_assignment.domain.entity.Bookmark(bookmark.getId(), bookmark.getPhotoId(),
                         bookmark.getImageUrl()));
             }
        }
        return domainBookmarks;
    }

    @Override
    public void addBookmark(Photo photo) {
        Bookmark entity = new Bookmark(photo.getId(), photo.getImageUrl());
        bookmarkDao.insert(entity);
    }

    @Override
    public void removeBookmark(Photo photo) {
        bookmarkDao.delete(photo.getId());
    }
}
