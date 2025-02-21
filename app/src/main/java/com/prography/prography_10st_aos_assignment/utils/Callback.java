package com.prography.prography_10st_aos_assignment.utils;

public interface Callback<T> {
    void onResponse(T result);
    void onFailure(Throwable result);
}