package com.prography.prography_10st_aos_assignment.data.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {
    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("portfolio_url")
    private String portfolioUrl;

    @SerializedName("bio")
    private String bio;

    @SerializedName("location")
    private String location;

    @SerializedName("total_likes")
    private int totalLikes;

    @SerializedName("total_photos")
    private int totalPhotos;

    @SerializedName("total_collections")
    private int totalCollections;

    @SerializedName("instagram_username") // Photo List 응답에만 포함됨
    private String instagramUsername;

    @SerializedName("twitter_username") // Photo List 응답에만 포함됨
    private String twitterUsername;

    @SerializedName("profile_image") // Photo List 응답에만 포함됨
    private ProfileImageDto profileImage;

    @SerializedName("links")
    private UserLinksDto links;

    public String getName() {
        return name;
    }
}
