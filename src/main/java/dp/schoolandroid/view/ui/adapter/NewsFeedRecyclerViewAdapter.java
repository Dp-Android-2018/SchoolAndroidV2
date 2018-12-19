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
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.view.ui.viewholder.NewsFeedViewHolder;

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<NewsFeedViewHolder> {
//    private ArrayList<FeedModel> feedModels=getDummyData();
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

    private ArrayList<FeedModel> getDummyData() {
        ArrayList<FeedModel> dummyData = new ArrayList<>();

        dummyData.add(new FeedModel("Java1", "Mosaid1", "20", "safg rsfkgkmfdl sa;ldfksmd;l fsdlm"));
        dummyData.add(new FeedModel("Java1", "Mosaid1", "20", "sa,dmldm  laksdfmld ldfm; sdlfksd"));
        dummyData.add(new FeedModel("Java1", "Mosaid1", "20", "sa,dmldm  laksdfmld ldfm; sdlfksd"));
        dummyData.add(new FeedModel("Java1", "Mosaid1", "20", "sa,dmldm  laksdfmld ldfm; sdlfksd"));
        dummyData.add(new FeedModel("Java1", "Mosaid1", "20", "sa,dmldm  laksdfmld ldfm; sdlfksd"));

        return dummyData;
    }
}
