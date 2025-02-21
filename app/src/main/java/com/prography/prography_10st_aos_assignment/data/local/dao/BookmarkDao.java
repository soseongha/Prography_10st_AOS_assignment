package com.prography.prography_10st_aos_assignment.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.prography.prography_10st_aos_assignment.data.local.entity.Bookmark;

import java.util.List;

@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM bookmarks WHERE photoId = :photoId")
    Bookmark getBookmarkByPhotoId(String photoId);

    @Query("SELECT * FROM bookmarks")
    List<Bookmark> getBookmarksAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Bookmark bookmark);

    @Query("DELETE FROM bookmarks WHERE photoId = :photoId")
    void delete(String photoId);
}
