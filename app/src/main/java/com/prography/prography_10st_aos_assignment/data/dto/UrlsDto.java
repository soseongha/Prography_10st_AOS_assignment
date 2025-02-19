package com.prography.prography_10st_aos_assignment.data.dto;

import com.google.gson.annotations.SerializedName;

public class UrlsDto {
    @SerializedName("raw")
    private String raw;

    @SerializedName("full")
    private String full;

    @SerializedName("regular")
    private String regular;

    @SerializedName("small")
    private String small;

    @SerializedName("thumb")
    private String thumb;

    public String getRaw() {
        return raw;
    }

    public String getSmall() {
        return small;
    }
}
