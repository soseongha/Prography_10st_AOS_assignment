package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prography.prography_10st_aos_assignment.R;
import com.prography.prography_10st_aos_assignment.domain.entity.Bookmark;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;

import java.util.ArrayList;

public class MainBookmarkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Bookmark> bookmarks;
    private Context context;
    private MainFragment mainFragment;

    public void setBookmarks(ArrayList<Bookmark> bookmarks, Context context, MainFragment mainFragment) {
        this.bookmarks = bookmarks;
        this.context = context;
        this.mainFragment = mainFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_bookmark, parent, false);
        return new MainBookmarkAdapter.MainBookmarkViewHolder(view, context, mainFragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bookmark bookmark = bookmarks.get(position);
        ((MainBookmarkAdapter.MainBookmarkViewHolder) holder).bind(bookmark);

    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    static class MainBookmarkViewHolder extends RecyclerView.ViewHolder {
        private Bookmark bookmark;
        private ImageView bookmarkImageview;
        private ConstraintLayout bookmarkTop;
        private Context context;
        private MainFragment mainFragment;

        public MainBookmarkViewHolder(@NonNull View itemView, Context context, MainFragment mainFragment) {
            super(itemView);
            bookmarkImageview = itemView.findViewById(R.id.imageview_bookmark);
            bookmarkTop = itemView.findViewById(R.id.top_bookmark);
            this.context = context;
            this.mainFragment = mainFragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Bookmark bookmark) {
            this.bookmark = bookmark;
            Glide.with(context)
                    .load(bookmark.getImageUrl())
                    .into(bookmarkImageview);
            bookmarkTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainFragment.onBookmarkPhotoClicked(bookmark.getPhotoId());
                }
            });
        }
    }
}
