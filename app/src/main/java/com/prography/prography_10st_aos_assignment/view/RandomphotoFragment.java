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

import com.prography.prography_10st_aos_assignment.data.dto.TagDto;
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
import java.util.List;

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

        int itemMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        int previewMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        setPreviewOfViewPager(viewpager, itemMargin, previewMargin);

        viewModel.getRandomPhoto().observe(getViewLifecycleOwner(), fetchedPhoto -> {
            if(fetchedPhoto == null){
                Toast.makeText(context, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
                /**
                 * TODO: 삭제해야 하는 코드
                 */
                fetchedPhoto = new Photo("a1jfkd", "example", "example title", "ssh", "https://r1.community.samsung.com/t5/image/serverpage/image-id/141368i8F105F6B57DB0E3D/image-size/large?v=v2&px=999", "https://r1.community.samsung.com/t5/image/serverpage/image-id/141368i8F105F6B57DB0E3D/image-size/large?v=v2&px=999", null);
                photos.add(fetchedPhoto);
                adapter.notifyItemInserted(photos.size() - 1);
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

    }

    public void onBookmarkButtonClicked(){
        viewpager.setCurrentItem(viewpager.getCurrentItem()+1, true);
        //TODO 북마크에 저장 로직 추가 요망
    }

    public void onInfoButtonClicked(String photoId){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra("photoId", photoId);
        startActivity(intent);
    }
}
