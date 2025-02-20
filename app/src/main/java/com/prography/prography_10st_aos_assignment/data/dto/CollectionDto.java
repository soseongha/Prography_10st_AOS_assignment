package com.prography.prography_10st_aos_assignment.data.dto;

import com.google.gson.annotations.SerializedName;

public class CollectionDto {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }
}
