package com.prography.prography_10st_aos_assignment.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bookmark.class}, version = 1)
public abstract class BookmarkDatabase extends RoomDatabase {
    private static volatile BookmarkDatabase INSTANCE;

    public abstract BookmarkDao bookmarkDao();

    public static BookmarkDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BookmarkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BookmarkDatabase.class, "bookmark_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}