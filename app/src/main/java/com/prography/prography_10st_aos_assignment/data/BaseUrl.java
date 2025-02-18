package com.prography.prography_10st_aos_assignment.data;

public enum BaseUrl {
    BASE_URL("https://api.unsplash.com/");

    private String url;

    BaseUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}