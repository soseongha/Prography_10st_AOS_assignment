package com.prography.prography_10st_aos_assignment.view;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prography.prography_10st_aos_assignment.R;
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

import com.prography.prography_10st_aos_assignment.domain.entity.Photo;

import java.util.ArrayList;

public class MainNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Photo> photos;
    private Context context;
    private MainFragment mainFragment;

    public void setPhotos(ArrayList<Photo> photos, Context context, MainFragment mainFragment) {
        this.photos = photos;
        this.context = context;
        this.mainFragment = mainFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_new, parent, false);
        return new MainNewAdapter.MainNewViewHolder(view, context, mainFragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        ((MainNewAdapter.MainNewViewHolder) holder).bind(photo);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class MainNewViewHolder extends RecyclerView.ViewHolder {
        private Photo photo;
        private ImageView newImageview;
        private TextView newTextview;
        private ConstraintLayout newTop;
        private Context context;
        private MainFragment mainFragment;

        public MainNewViewHolder(@NonNull View itemView, Context context, MainFragment mainFragment) {
            super(itemView);
            newImageview = itemView.findViewById(R.id.imageview_new);
            newTextview = itemView.findViewById(R.id.textview_new);
            newTop = itemView.findViewById(R.id.top_new);
            this.context = context;
            this.mainFragment = mainFragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Photo photo) {
            this.photo = photo;
            newTextview.setText(photo.getTitle());
            Glide.with(context)
                    .load(photo.getImageUrl())
                    .into(newImageview);
            newImageview.setClipToOutline(true);
        }
    }
}
