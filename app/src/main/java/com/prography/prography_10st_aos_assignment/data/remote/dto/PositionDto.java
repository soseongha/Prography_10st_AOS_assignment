package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class PositionDto {
    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }
}
