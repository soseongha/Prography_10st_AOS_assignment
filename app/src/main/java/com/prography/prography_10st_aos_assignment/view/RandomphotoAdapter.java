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
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prography.prography_10st_aos_assignment.R;
import com.prography.prography_10st_aos_assignment.domain.entity.Photo;

import java.util.ArrayList;

public class RandomphotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Photo> photos;
    private Context context;
    private RandomphotoFragment randomphotoFragment;

    public void setPhotos(ArrayList<Photo> photos, Context context, RandomphotoFragment randomphotoFragment) {
        this.photos = photos;
        this.context = context;
        this.randomphotoFragment = randomphotoFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random_card, parent, false);
        return new RandomphotoAdapter.MainNewViewHolder(view, context, randomphotoFragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        ((RandomphotoAdapter.MainNewViewHolder) holder).bind(photo);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class MainNewViewHolder extends RecyclerView.ViewHolder {
        private Photo photo;
        private ImageView randomImageview;
        private AppCompatButton xButton;
        private AppCompatButton bookmarkButton;
        private AppCompatButton infoButton;
        private Context context;
        private RandomphotoFragment randomphotoFragment;

        public MainNewViewHolder(@NonNull View itemView, Context context, RandomphotoFragment randomphotoFragment) {
            super(itemView);
            randomImageview = itemView.findViewById(R.id.imageview_random);
            xButton = itemView.findViewById(R.id.button_x);
            bookmarkButton = itemView.findViewById(R.id.button_bookmark);
            infoButton = itemView.findViewById(R.id.button_info);
            this.context = context;
            this.randomphotoFragment = randomphotoFragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Photo photo) {
            this.photo = photo;
            Glide.with(context)
                    .load(photo.getImageUrl())
                    .into(randomImageview);
            randomImageview.setClipToOutline(true);
            xButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    randomphotoFragment.onXButtonClicked();
                }
            });
            bookmarkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    randomphotoFragment.onBookmarkButtonClicked();
                }
            });
            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    randomphotoFragment.onInfoButtonClicked(photo.getId());
                }
            });
        }
    }
}
