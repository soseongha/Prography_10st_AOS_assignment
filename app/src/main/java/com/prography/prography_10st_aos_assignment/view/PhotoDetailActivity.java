package com.prography.prography_10st_aos_assignment.view;

import android.app.Activity;
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
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoDetailViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoDetailViewModelFactory;

public class PhotoDetailActivity extends AppCompatActivity {
    private ActivityPhotodetailBinding binding;
    private PhotoDetailViewModel viewModel;
    public String TAG = getClass().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotodetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
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
        viewModel.getPhoto().observe(this, photo -> {
            if(photo == null){
                Toast.makeText(this, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
            }
            else{
                binding.textviewUsername.setText(photo.getUsername());
                binding.textviewTitle.setText(photo.getTitle());
                binding.textviewDescription.setText(photo.getDescription());
                String tags = "";
                if(photo.getTags() != null) {
                    for (String tag : photo.getTags()) {
                        tags = tags.concat(" #" + tag);
                    }
                }
                binding.textviewTag.setText(tags);
                Glide.with(this).load(photo.getImageUrl()).into(binding.imageviewDetail);
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
                //TODO
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
