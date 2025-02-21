package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class UserLinksDto {
    @SerializedName("self")
    private String self;

    @SerializedName("html")
    private String html;

    @SerializedName("photos")
    private String photos;

    @SerializedName("likes")
    private String likes;

    @SerializedName("portfolio")
    private String portfolio;
}
