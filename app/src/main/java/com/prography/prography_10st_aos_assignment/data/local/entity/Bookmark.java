package com.prography.prography_10st_aos_assignment.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class Bookmark {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String photoId;
    private String imageUrl;

    public Bookmark(String photoId, String imageUrl) {
        this.photoId = photoId;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
}