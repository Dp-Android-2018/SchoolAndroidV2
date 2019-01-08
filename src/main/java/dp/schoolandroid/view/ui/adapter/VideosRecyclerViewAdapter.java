package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemPictureLayoutBinding;
import dp.schoolandroid.databinding.ItemVideoLayoutBinding;
import dp.schoolandroid.service.model.global.VideosModel;
import dp.schoolandroid.view.ui.viewholder.PictureGalleryViewHolder;
import dp.schoolandroid.view.ui.viewholder.VideosViewHolder;

public class VideosRecyclerViewAdapter extends RecyclerView.Adapter<VideosViewHolder> {
    private List<VideosModel> pageVideos;

    public VideosRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_video_layout, parent, false);
        return new VideosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {
        holder.bindClass(pageVideos.get(position));
    }

    @Override
    public int getItemCount() {
        if (pageVideos !=null){
            return pageVideos.size();
        }else {
            return 0;
        }
    }

    public void setPageVideos(List<VideosModel> pageVideos) {
        this.pageVideos = pageVideos;
    }
}
