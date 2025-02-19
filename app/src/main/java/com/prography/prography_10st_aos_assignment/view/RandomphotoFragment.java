package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.databinding.FragmentRandomphotoBinding;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.utils.ViewPagerItemDecoration;
import com.prography.prography_10st_aos_assignment.viewmodel.MainPhotoViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.MainPhotoViewModelFactory;
import com.prography.prography_10st_aos_assignment.viewmodel.RandomPhotoViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.RandomPhotoViewModelFactory;

import java.util.ArrayList;

public class RandomphotoFragment extends Fragment {
    private FragmentRandomphotoBinding binding;
    private RandomPhotoViewModel viewModel;
    private Context context;
    ViewPager2 viewpager;
    private final String TAG = getClass().toString();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRandomphotoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();

        GetRandomPhotoUsecase getRandomPhotoUsecase = new GetRandomPhotoUsecase(new UnsplashRepositoryImpl());
        RandomPhotoViewModelFactory factory = new RandomPhotoViewModelFactory(getRandomPhotoUsecase);
        viewModel = new ViewModelProvider(this, factory).get(RandomPhotoViewModel.class);

        loadRandomPhotos();

        return root;
    }

    private void loadRandomPhotos(){
        ArrayList<Photo> photos = new ArrayList<>();
        viewpager = binding.viewpagerRandomphoto;
        RandomphotoAdapter adapter = new RandomphotoAdapter();
        adapter.setPhotos(photos, context, this);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(1);
        int pageMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        viewpager.addItemDecoration(new ViewPagerItemDecoration(pageMarginPx));
        viewpager.setPageTransformer((page, position) -> {
            float scaleFactor = 1 - 0.1f * Math.abs(position);
            page.setScaleY(scaleFactor);
            page.setAlpha(0.8f + (1 - Math.abs(position)) * 0.2f);
        });
        viewpager.setClipToPadding(false);
        viewpager.setClipChildren(false);

        viewModel.getRandomPhoto().observe(getViewLifecycleOwner(), fetchedPhoto -> {
            if(fetchedPhoto == null){
                Toast.makeText(context, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
            }
            else{
                photos.add(fetchedPhoto);
                adapter.notifyItemInserted(photos.size() - 1);
            }
        });
        viewModel.fetchRandomPhoto();
        new Handler(Looper.getMainLooper()).postDelayed(() ->
                viewModel.fetchRandomPhoto(), 50);

        //마지막 페이지 도달 시, 사진 하나 load
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                new Handler().postDelayed(() -> {
                    if (!photos.isEmpty() && position == photos.size() - 1) {
                        viewModel.fetchRandomPhoto();
                    }
                }, 50);
            }
        });
    }

    public void onXButtonClicked(){

    }

    public void onBookmarkButtonClicked(){
        viewpager.setCurrentItem(viewpager.getCurrentItem()+1, true);
        //TODO 북마크에 저장 로직 추가 요망
    }

    public void onInfoButtonClicked(){

    }
}
