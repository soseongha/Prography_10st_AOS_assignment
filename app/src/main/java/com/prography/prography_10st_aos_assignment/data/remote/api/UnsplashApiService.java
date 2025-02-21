package com.prography.prography_10st_aos_assignment.data.remote.api;

import com.prography.prography_10st_aos_assignment.data.remote.dto.PhotoDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnsplashApiService {
    @GET("/photos")
    Call<List<PhotoDto>> getPhotos(
            @Query("client_id") String clientId,
            @Query("page") Integer page,
            @Query("per_page") Integer per_page
    );

    @GET("/photos/random")
    Call<PhotoDto> getRandomPhoto(
            @Query("client_id") String clientId
    );

    @GET("/photos/{id}")
    Call<PhotoDto> getPhoto(
            @Path("id") String id,
            @Query("client_id") String clientId

    );
}
