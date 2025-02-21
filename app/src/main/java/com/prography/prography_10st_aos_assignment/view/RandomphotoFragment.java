package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.content.Intent;
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

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.LocalRepositoryImpl;
import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.databinding.FragmentRandomphotoBinding;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;
import com.prography.prography_10st_aos_assignment.utils.ViewPagerItemDecoration;
import com.prography.prography_10st_aos_assignment.view.adapter.RandomphotoAdapter;
import com.prography.prography_10st_aos_assignment.viewmodel.RandomPhotoViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.factory.RandomPhotoViewModelFactory;

import dagger.hilt.android.AndroidEntryPoint;

import java.util.ArrayList;

@AndroidEntryPoint
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
        viewModel = new ViewModelProvider(this).get(RandomPhotoViewModel.class);

        loadRandomPhotos();

        return root;
    }

    private void loadRandomPhotos(){
        ArrayList<Photo> photos = new ArrayList<>();
        viewpager = binding.viewpagerRandomphoto;
        RandomphotoAdapter adapter = new RandomphotoAdapter();
        adapter.setPhotos(photos, context, this);
        viewpager.setAdapter(adapter);

        int itemMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        int previewMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        setPreviewOfViewPager(viewpager, itemMargin, previewMargin);

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

    private void setPreviewOfViewPager(ViewPager2 viewpager, int itemMargin, int previewMargin){
        int decoMargin = itemMargin + previewMargin;
        int pageTransX = decoMargin * 2;

        viewpager.setClipToPadding(false);
        viewpager.setClipChildren(false);
        viewpager.setOffscreenPageLimit(1);
        viewpager.addItemDecoration(new ViewPagerItemDecoration(decoMargin));
        viewpager.setPageTransformer((page, position) -> {
            page.setTranslationX(position * - pageTransX);
        });
    }

    public void onXButtonClicked(){
        /*아무 명세도 없음*/
        return;
    }

    public void onBookmarkButtonClicked(Photo photo){
        viewModel.getToggleBookmark().observe(this, bookmarked -> {
            if(bookmarked){
                Toast.makeText(context, "북마크에 추가되었어요!", Toast.LENGTH_SHORT).show();
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1, true);
            }
            else{
                Toast.makeText(context, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.toggleBookmark(photo, false);
    }

    public void onInfoButtonClicked(String photoId){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra("photoId", photoId);
        startActivity(intent);
    }
}
