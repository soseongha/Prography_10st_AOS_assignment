package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ExifDto {
    @SerializedName("make")
    private String make;

    @SerializedName("model")
    private String model;

    @SerializedName("name")
    private String name;

    @SerializedName("exposure_time")
    private String exposureTime;

    @SerializedName("aperture")
    private String aperture;

    @SerializedName("focal_length")
    private String focalLength;

    @SerializedName("iso")
    private int iso;

    public String getMake() {
        return make;
    }
}
