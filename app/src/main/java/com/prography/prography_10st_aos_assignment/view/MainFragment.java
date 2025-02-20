package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.databinding.FragmentMainBinding;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.PhotoViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private PhotoViewModel viewModel;
    private Context context;
    private Boolean isLoading = false;
    private final String TAG = getClass().toString();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();

        GetPhotosUsecase getPhotosUsecase = new GetPhotosUsecase(new UnsplashRepositoryImpl());
        PhotoViewModelFactory factory = new PhotoViewModelFactory(getPhotosUsecase);
        viewModel = new ViewModelProvider(this, factory).get(PhotoViewModel.class);

        showSkeleton(true);
        loadNewPhotos();
        loadBookmarks();

        return root;
    }

    private void loadNewPhotos(){
        binding.itemTitleNew.textviewTitle.setText("최신 이미지");

        ArrayList<Photo> photos = new ArrayList<>();
        RecyclerView newRecycler = binding.recyclerNew;
        MainNewAdapter mainNewAdapter = new MainNewAdapter();
        mainNewAdapter.setPhotos(photos, context, this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        newRecycler.setLayoutManager(layoutManager);
        newRecycler.setAdapter(mainNewAdapter);

        viewModel.clearPage();
        viewModel.getPhotos().observe(getViewLifecycleOwner(), fetchedPhotos -> {
            if(fetchedPhotos == null){
                Toast.makeText(context, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
            }
            else{
                photos.addAll(fetchedPhotos);
                mainNewAdapter.notifyItemInserted(photos.size() - 1);
            }
            showSkeleton(false);
        });
        viewModel.fetchPhotos();

        /*무한스크롤*/
        long DEBOUNCE_DELAY = 0;
        Handler handler = new Handler(Looper.getMainLooper());
        newRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
                    if (photos.isEmpty()) {
                        return;
                    } else {
                        isLoading = true;
                        viewModel.fetchPhotos();
                        handler.postDelayed(() -> isLoading = false, DEBOUNCE_DELAY);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadBookmarks() {
        binding.itemTitleBookmark.textviewTitle.setText("북마크");
    }

    private void showSkeleton(boolean isLoading) {
        if(isLoading){
            binding.shimmerBookmark.startShimmer();
            binding.shimmerNew.startShimmer();
            binding.shimmerBookmark.setVisibility(View.VISIBLE);
            binding.recyclerBookmark.setVisibility(View.GONE);
            binding.shimmerNew.setVisibility(View.VISIBLE);
            binding.recyclerNew.setVisibility(View.GONE);
        }
        else{
            binding.shimmerBookmark.stopShimmer();
            binding.shimmerNew.stopShimmer();
            binding.shimmerBookmark.setVisibility(View.GONE);
            binding.recyclerBookmark.setVisibility(View.VISIBLE);
            binding.shimmerNew.setVisibility(View.GONE);
            binding.recyclerNew.setVisibility(View.VISIBLE);
        }

    }
}
