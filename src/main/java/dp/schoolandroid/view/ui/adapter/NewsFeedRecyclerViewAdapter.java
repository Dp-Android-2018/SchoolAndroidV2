package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemFeedBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.viewholder.NewsFeedViewHolder;

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<NewsFeedViewHolder> {
    private ArrayList<FeedModel> feedModels;

    public NewsFeedRecyclerViewAdapter(ArrayList<FeedModel> feedModels) {
        this.feedModels=feedModels;
    }

    @NonNull
    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_feed,parent,false);
        return new NewsFeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        holder.bindClass(feedModels.get(position));
    }

    @Override
    public int getItemCount() {
        if (feedModels !=null){
            return feedModels.size();
        }else {
            return 0;
        }
    }
}
