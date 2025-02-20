package com.prography.prography_10st_aos_assignment.data.repositoryImpl;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import com.prography.prography_10st_aos_assignment.BuildConfig;
import com.prography.prography_10st_aos_assignment.data.RetrofitClient;
import com.prography.prography_10st_aos_assignment.data.api.UnsplashApiService;
import com.prography.prography_10st_aos_assignment.data.dto.PhotoDto;
import com.prography.prography_10st_aos_assignment.data.dto.TagDto;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.repository.UnsplashRepository;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnsplashRepositoryImpl implements UnsplashRepository {
    private final UnsplashApiService apiService;
    private final Retrofit retrofitClient;
    private final String clientId = BuildConfig.UNSPLASH_ACCESS_KEY;
    private final String TAG = getClass().toString();

    public UnsplashRepositoryImpl() {
        retrofitClient = RetrofitClient.getClient();
        apiService = retrofitClient.create(UnsplashApiService.class);
    }

    @Override
    public void getPhotos(int page, int perPage, com.prography.prography_10st_aos_assignment.utils.Callback<List<Photo>> callback) {
        Call<List<PhotoDto>> result = apiService.getPhotos(clientId, page, perPage);

        result.enqueue(new retrofit2.Callback<List<PhotoDto>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<PhotoDto>> call, Response<List<PhotoDto>> response) {
                if (response.isSuccessful()) {
                    List<PhotoDto> responseBody = response.body();
                    List<Photo> photos = new ArrayList<>();
                    for (PhotoDto dto : responseBody) {
                        List<String> tags = new ArrayList<>();
                        if(dto.getTags() != null){
                            for(TagDto tagDto : dto.getTags()){
                                tags.add(tagDto.getTitle());
                            }
                        }
                        photos.add(new Photo(dto.getId(), dto.getDescription(), dto.getSlug(), dto.getUser().getName(), dto.getUrls().getSmall(), dto.getLinks().getDownload(), tags));
                    }
                    callback.onResponse(photos);
                } else {
                    callback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<List<PhotoDto>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getRandomPhoto(Callback<Photo> callback) {
        Call<PhotoDto> result = apiService.getRandomPhoto(clientId);

        result.enqueue(new retrofit2.Callback<PhotoDto>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<PhotoDto> call, Response<PhotoDto> response) {
                if (response.isSuccessful()) {
                    PhotoDto dto = response.body();
                    List<String> tags = new ArrayList<>();
                    if(dto.getTags() != null){
                        for(TagDto tagDto : dto.getTags()){
                            tags.add(tagDto.getTitle());
                        }
                    }
                    Photo photo = new Photo(dto.getId(), dto.getDescription(), dto.getSlug(), dto.getUser().getName(), dto.getUrls().getSmall(), dto.getLinks().getDownload(), tags);
                    callback.onResponse(photo);
                } else {
                    callback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PhotoDto> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getPhoto(String id, Callback<Photo> callback) {
        Call<PhotoDto> result = apiService.getPhoto(id, clientId);

        result.enqueue(new retrofit2.Callback<PhotoDto>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<PhotoDto> call, Response<PhotoDto> response) {
                if (response.isSuccessful()) {
                    PhotoDto dto = response.body();
                    List<String> tags = new ArrayList<>();
                    if(dto.getTags() != null){
                        for(TagDto tagDto : dto.getTags()){
                            tags.add(tagDto.getTitle());
                        }
                    }
                    Photo photo = new Photo(dto.getId(), dto.getDescription(), dto.getSlug(), dto.getUser().getName(), dto.getUrls().getSmall(), dto.getLinks().getDownload(), tags);
                    callback.onResponse(photo);
                } else {
                    callback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PhotoDto> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
