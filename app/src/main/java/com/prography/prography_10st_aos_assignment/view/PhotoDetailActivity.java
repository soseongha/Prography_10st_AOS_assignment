package com.prography.prography_10st_aos_assignment.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.databinding.ActivityPhotodetailBinding;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoDetailViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoDetailViewModelFactory;

public class PhotoDetailActivity extends AppCompatActivity {
    private ActivityPhotodetailBinding binding;
    private PhotoDetailViewModel viewModel;
    private Context context;
    private Photo photo;
    public String TAG = getClass().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotodetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        this.context = this;
        setContentView(view);

        GetPhotoUsecase getPhotoUsecase = new GetPhotoUsecase(new UnsplashRepositoryImpl());
        PhotoDetailViewModelFactory factory = new PhotoDetailViewModelFactory(getPhotoUsecase);
        viewModel = new ViewModelProvider(this, factory).get(PhotoDetailViewModel.class);

        loadPhoto();
        initClose();
        initDownload();
        initBookmark();
    }

    private void loadPhoto(){
        Intent prevIntent = getIntent();
        String photoId = prevIntent.getStringExtra("photoId");
        if(photoId == null){
            Toast.makeText(this, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        viewModel.fetchPhoto(photoId);
        viewModel.getPhoto().observe(this, fetchedPhoto -> {
            if(fetchedPhoto == null){
                Toast.makeText(this, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
            }
            else{
                binding.textviewUsername.setText(fetchedPhoto.getUsername());
                binding.textviewTitle.setText(fetchedPhoto.getTitle());
                binding.textviewDescription.setText(fetchedPhoto.getDescription());
                String tags = "";
                if(fetchedPhoto.getTags() != null) {
                    for (String tag : fetchedPhoto.getTags()) {
                        tags = tags.concat(" #" + tag);
                    }
                }
                binding.textviewTag.setText(tags);
                Glide.with(this).load(fetchedPhoto.getImageUrl()).into(binding.imageviewDetail);
                photo = fetchedPhoto;
            }
        });
    }

    private void initClose(){
        binding.buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initDownload(){
        binding.buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.downloadPhoto(context, photo.getDownloadUrl(), photo.getTitle());
            }
        });
    }

    private void initBookmark(){
        binding.buttonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

}
