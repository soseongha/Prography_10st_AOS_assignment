package com.prography.prography_10st_aos_assignment.domain.entity;

import com.prography.prography_10st_aos_assignment.data.dto.TagDto;

import java.util.List;

public class Photo {
    private final String id;
    private final String description;
    private final String title;
    private List<String> tags;
    private final String username;
    private final String imageUrl;
    private final String downloadUrl;

    public Photo(String id, String description, String title, String username, String imageUrl, String downloadUrl, List<String> tags) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.username = username;
        this.imageUrl = imageUrl;
        this.downloadUrl = downloadUrl;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
}

