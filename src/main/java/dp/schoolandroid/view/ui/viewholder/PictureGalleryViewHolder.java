package dp.schoolandroid.view.ui.viewholder;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;

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
        AlertDialog.Builder alertadd = new AlertDialog.Builder(binding.getRoot().getContext());
        LayoutInflater factory = LayoutInflater.from(binding.getRoot().getContext());
        final View view = factory.inflate(R.layout.picture_layout_view, null);
        ImageView imgView = (ImageView)view.findViewById(R.id.dialog_imageview);
        Picasso.get().load(pageImage).into(imgView);
        alertadd.setView(view);
        alertadd.show();
    }
}
