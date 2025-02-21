package com.prography.prography_10st_aos_assignment.viewmodel;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.IsBookmarkedUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.utils.Callback;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

@HiltViewModel
public class PhotoDetailViewModel extends ViewModel {
    private final GetPhotoUsecase getPhotoUsecase;
    private final IsBookmarkedUsecase isBookmarkedUsecase;
    private final ToggleBookmarkUsecase toggleBookmarkUsecase;
    private final MutableLiveData<Photo> photoLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isBookmarkedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> toggleBookmarkLiveData = new MutableLiveData<>();
    private final String TAG = getClass().toString();

    @Inject
    public PhotoDetailViewModel(GetPhotoUsecase getPhotoUsecase, IsBookmarkedUsecase isBookmarkedUsecase, ToggleBookmarkUsecase toggleBookmarkUsecase) {
        this.getPhotoUsecase = getPhotoUsecase;
        this.isBookmarkedUsecase = isBookmarkedUsecase;
        this.toggleBookmarkUsecase = toggleBookmarkUsecase;
    }

    public MutableLiveData<Photo> getPhoto() {
        return photoLiveData;
    }

    public MutableLiveData<Boolean> getIsBookmarked() {
        return isBookmarkedLiveData;
    }

    public MutableLiveData<Boolean> getToggleBookmark() {
        return toggleBookmarkLiveData;
    }

    public void setPhoto(Photo photo){
        photoLiveData.setValue(photo);
    }

    public void setIsBookmarked(Boolean isBookmarked){
        isBookmarkedLiveData.postValue(isBookmarked);
    }

    public void setToggleBookmark(Boolean toggleBookmark){
        toggleBookmarkLiveData.postValue(toggleBookmark);
    }

    public void fetchPhoto(String id) {
        getPhotoUsecase.execute(id, new Callback<Photo>() {
            @Override
            public void onResponse(Photo result) {
                setPhoto(result);
            }
            @Override
            public void onFailure(Throwable result) {
                setPhoto(null);
            }
        });
    }

    public void downloadPhoto(Context context, String downloadUrl, String fileName){
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            request.setTitle("이미지 다운로드");
            request.setDescription("다운로드중: " + fileName);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName);

            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
                Toast.makeText(context, "다운로드 시작", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "다운로드 실패: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void loadBookmarkStatus(String photoId){
        new Thread(() -> {
            boolean isBookmarked = isBookmarkedUsecase.execute(photoId);
            setIsBookmarked(isBookmarked);
        }).start();
    }

    public void toggleBookmark(Photo photo, Boolean isBookmarked){
        new Thread(() -> {
            boolean bookmarked = toggleBookmarkUsecase.execute(photo, isBookmarked);
            setToggleBookmark(bookmarked);
        }).start();
    }
}
