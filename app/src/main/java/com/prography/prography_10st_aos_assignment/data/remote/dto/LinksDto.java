package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LinksDto {
    @SerializedName("self")
    private String self;

    @SerializedName("html")
    private String html;

    @SerializedName("download")
    private String download;

    @SerializedName("download_location")
    private String downloadLocation;

    public String getDownload() {
        return download;
    }
}
