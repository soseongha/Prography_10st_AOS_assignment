package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ProfileImageDto {
    @SerializedName("small")
    private String small;

    @SerializedName("medium")
    private String medium;

    @SerializedName("large")
    private String large;

    public String getSmall() {
        return small;
    }
}
