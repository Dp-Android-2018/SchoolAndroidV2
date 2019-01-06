package dp.schoolandroid.view.ui.viewholder;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemFeedBinding;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;
import dp.schoolandroid.service.model.global.FeedModel;

public class PictureGalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemPictureLayoutBinding binding;
    private String pageImage;

    public PictureGalleryViewHolder(ItemPictureLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindClass(String pageImage) {
        this.pageImage=pageImage;
        ImageView ivGalleryPhoto = binding.galleryPicture;
        Picasso.get().load(pageImage).into(ivGalleryPhoto);
        ivGalleryPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(binding.getRoot(), "Item click nr: " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertadd = new AlertDialog.Builder(binding.getRoot().getContext());
        LayoutInflater factory = LayoutInflater.from(binding.getRoot().getContext());
        final View view = factory.inflate(R.layout.picture_layout_view, null);
        ImageView imgView = (ImageView)view.findViewById(R.id.dialog_imageview);
        Picasso.get().load(pageImage).into(imgView);
        alertadd.setView(view);
        alertadd.show();
    }
}
