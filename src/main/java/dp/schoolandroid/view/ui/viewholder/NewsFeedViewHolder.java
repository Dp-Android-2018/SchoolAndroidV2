package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemFeedBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.activity.FeedDetailsActivity;
import dp.schoolandroid.viewmodel.ItemFeedViewModel;

public class NewsFeedViewHolder extends RecyclerView.ViewHolder {
    private ItemFeedBinding binding;

    public NewsFeedViewHolder(ItemFeedBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindClass(FeedModel feedModel) {
        if (binding.getViewModel() == null) {
            binding.setViewModel(new ItemFeedViewModel(feedModel));
        } else {
            binding.getViewModel().feedModel = feedModel;
        }
        initializeUi(binding, feedModel);
    }

    private void initializeUi(ItemFeedBinding binding, final FeedModel feedModel) {
        ImageView ivFeedPhoto = binding.ivFeedPhoto;
        Picasso.get().load(feedModel.getImage()).into(ivFeedPhoto);
        binding.tvFeedReadMore.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), FeedDetailsActivity.class);
            Gson gson = new Gson();
            intent.putExtra(ConfigurationFile.Constants.DATA, gson.toJson(feedModel));
            v.getContext().startActivity(intent);
        });
    }
}
