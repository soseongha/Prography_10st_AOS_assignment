package com.prography.prography_10st_aos_assignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetBookmarksUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import dagger.hilt.android.lifecycle.HiltViewModel;

import java.util.List;

import javax.inject.Inject;

@HiltViewModel
public class MainPhotoViewModel extends ViewModel {
    private final GetPhotosUsecase getPhotosUsecase;
    private final GetBookmarksUsecase getBookmarksUsecase;
    private final MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Bookmark>> bookmarksLiveData = new MutableLiveData<>();
    private int page;
    private int perPage;
    private final String TAG = getClass().toString();

    @Inject
    public MainPhotoViewModel(GetPhotosUsecase getPhotosUsecase, GetBookmarksUsecase getBookmarksUsecase) {
        this.getPhotosUsecase = getPhotosUsecase;
        this.getBookmarksUsecase = getBookmarksUsecase;
        page = 1;
        perPage = 10;
    }

    public LiveData<List<Photo>> getPhotos() {
        return photosLiveData;
    }

    public LiveData<List<Bookmark>> getBookmarks() { return bookmarksLiveData; }

    public void setPhotos(List<Photo> photos){
        photosLiveData.setValue(photos);
    }

    public void setBookmarks(List<Bookmark> bookmarks){
        bookmarksLiveData.postValue(bookmarks);
    }

    public void fetchPhotos() {
        getPhotosUsecase.execute(page, perPage, new Callback<List<Photo>>() {
            @Override
            public void onResponse(List<Photo> result) {
                setPhotos(result);
                page++;
            }
            @Override
            public void onFailure(Throwable result) {
                setPhotos(null);
            }
        });
    }

    public void clearPage(){
        page = 1;
    }

    public void fetchBookmarks(){
        new Thread(() -> {
            List<Bookmark> bookmarks = getBookmarksUsecase.execute();
            setBookmarks(bookmarks);
        }).start();
    }
}
