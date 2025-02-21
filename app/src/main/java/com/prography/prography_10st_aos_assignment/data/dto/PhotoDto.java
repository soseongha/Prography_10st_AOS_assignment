package com.prography.prography_10st_aos_assignment.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PhotoDto {
    @SerializedName("id")
    private String id;

    @SerializedName("slug")
    private String slug;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("color")
    private String color;

    @SerializedName("blur_hash")
    private String blurHash;

    @SerializedName("likes")
    private int likes;

    @SerializedName("liked_by_user")
    private boolean likedByUser;

    @SerializedName("description")
    private String description;

    @Nullable
    @SerializedName("downloads")
    private Integer downloads;

    @SerializedName("user")
    private UserDto user;

    @Nullable
    @SerializedName("exif")
    private ExifDto exif;

    @Nullable
    @SerializedName("location")
    private LocationDto location;

    @Nullable
    @SerializedName("tags")
    private List<TagDto> tags;

    @SerializedName("current_user_collections")
    private List<CollectionDto> currentUserCollections;

    @SerializedName("urls")
    private UrlsDto urls;

    @SerializedName("links")
    private LinksDto links;

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public UserDto getUser() {
        return user;
    }

    public UrlsDto getUrls() {
        return urls;
    }

    public LinksDto getLinks() {
        return links;
    }
}
