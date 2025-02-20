package com.prography.prography_10st_aos_assignment.domain.entity;

public class Bookmark {
    private final Integer id;
    private final String photoId;
    private final String imageUrl;

    public Bookmark(Integer id, String photoId, String imageUrl){
        this.id = id;
        this.photoId = photoId;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhotoId() {
        return photoId;
    }
}
