package com.prography.prography_10st_aos_assignment.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LocationDto {
    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("position")
    private PositionDto position;

    public String getCity() {
        return city;
    }
}
