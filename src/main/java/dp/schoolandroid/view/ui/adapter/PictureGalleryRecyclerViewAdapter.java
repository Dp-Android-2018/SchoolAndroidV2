package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;
import dp.schoolandroid.view.ui.viewholder.PictureGalleryViewHolder;

public class PictureGalleryRecyclerViewAdapter extends RecyclerView.Adapter<PictureGalleryViewHolder> {
    private List<String> pageImages;

    public PictureGalleryRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public PictureGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPictureLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_picture_layout, parent, false);
        return new PictureGalleryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureGalleryViewHolder holder, int position) {
        holder.bindClass(pageImages.get(position));
    }

    @Override
    public int getItemCount() {
        if (pageImages !=null){
            return pageImages.size();
        }else {
            return 0;
        }
    }

    public void setPageImages(List<String> pageImages) {
        this.pageImages = pageImages;
    }
}
