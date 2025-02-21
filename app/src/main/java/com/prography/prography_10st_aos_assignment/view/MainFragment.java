package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.LocalRepositoryImpl;
import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.databinding.FragmentMainBinding;
import com.prography.prography_10st_aos_assignment.domain.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetBookmarksUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.utils.WrapContentLinearLayoutManager;
import com.prography.prography_10st_aos_assignment.view.adapter.MainBookmarkAdapter;
import com.prography.prography_10st_aos_assignment.view.adapter.MainNewAdapter;
import com.prography.prography_10st_aos_assignment.viewmodel.MainPhotoViewModel;
import com.prography.prography_10st_aos_assignment.viewmodel.factory.MainPhotoViewModelFactory;

import dagger.hilt.android.AndroidEntryPoint;

import java.util.ArrayList;

@AndroidEntryPoint
public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private MainPhotoViewModel viewModel;
    private Context context;
    private Boolean isLoading = false;
    private final String TAG = getClass().toString();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();
        viewModel = new ViewModelProvider(this).get(MainPhotoViewModel.class);

        showSkeleton(true);
        loadNewPhotos();
        loadBookmarks();

        return root;
    }

    private void loadNewPhotos() {
        binding.itemTitleNew.textviewTitle.setText("최신 이미지");

        ArrayList<Photo> photos = new ArrayList<>();
        RecyclerView newRecycler = binding.recyclerNew;
        MainNewAdapter mainNewAdapter = new MainNewAdapter();
        mainNewAdapter.setPhotos(photos, context, this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        newRecycler.setLayoutManager(layoutManager);
        newRecycler.setAdapter(mainNewAdapter);
        newRecycler.requestFocus();

        viewModel.clearPage();
        viewModel.getPhotos().observe(getViewLifecycleOwner(), fetchedPhotos -> {
            if (fetchedPhotos == null) {
                Toast.makeText(context, "호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "호출 실패");
            } else {
                photos.addAll(fetchedPhotos);
                mainNewAdapter.notifyItemInserted(photos.size() - 1);
            }
            showSkeleton(false);
        });
        viewModel.fetchPhotos();

        /*무한스크롤*/
        binding.scrollviewLayout.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) && !isLoading) {
                    isLoading = true;
                    viewModel.fetchPhotos();
                    isLoading = false;
                }
            }
        });
    }

    private void loadBookmarks() {
        binding.itemTitleBookmark.textviewTitle.setText("북마크");

        ArrayList<Bookmark> bookmarks = new ArrayList<>();
        RecyclerView bookmarkRecycler = binding.recyclerBookmark;
        MainBookmarkAdapter mainBookmarkAdapter = new MainBookmarkAdapter();
        mainBookmarkAdapter.setBookmarks(bookmarks, context, this);
        bookmarkRecycler.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        bookmarkRecycler.setAdapter(mainBookmarkAdapter);

        viewModel.getBookmarks().observe(getViewLifecycleOwner(), fetchedBookmarks -> {
            if(fetchedBookmarks == null || fetchedBookmarks.isEmpty()) {
                bookmarks.clear();
                binding.bookmarkLayout.setVisibility(View.GONE);
            }
            else{
                bookmarks.clear();
                bookmarks.addAll(fetchedBookmarks);
                mainBookmarkAdapter.notifyItemInserted(bookmarks.size() - 1);
            }
        });
        viewModel.fetchBookmarks();
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

    public void onNewPhotoClicked(String photoId){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra("photoId", photoId);
        startActivity(intent);
    }

    public void onBookmarkPhotoClicked(String photoId){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra("photoId", photoId);
        startActivity(intent);
    }
}
