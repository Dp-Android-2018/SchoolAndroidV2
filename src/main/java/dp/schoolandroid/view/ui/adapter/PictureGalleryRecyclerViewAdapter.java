package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemFeedBinding;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.viewholder.NewsFeedViewHolder;
import dp.schoolandroid.view.ui.viewholder.PictureGalleryViewHolder;

public class PictureGalleryRecyclerViewAdapter extends RecyclerView.Adapter<PictureGalleryViewHolder> {
    private ArrayList<String> pageImages;

    public PictureGalleryRecyclerViewAdapter(ArrayList<String> pageImages) {
        this.pageImages=pageImages;
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
}
