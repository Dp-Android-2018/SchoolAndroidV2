package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;
import dp.schoolandroid.databinding.ItemVideoLayoutBinding;
import dp.schoolandroid.service.model.global.VideosModel;

public class VideosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ItemVideoLayoutBinding binding;
    private VideosModel pageVideos;

    public VideosViewHolder(ItemVideoLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindClass(VideosModel pageVideos) {
        this.pageVideos=pageVideos;
        ImageView ivGalleryPhoto = binding.videoPicture;
        ivGalleryPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            Uri uri = Uri.parse(pageVideos.getVideoUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            binding.getRoot().getContext().startActivity(intent);
    }
}
