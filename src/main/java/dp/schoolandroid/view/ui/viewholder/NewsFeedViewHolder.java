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

public class NewsFeedViewHolder extends RecyclerView.ViewHolder {
    private ItemFeedBinding binding;

    public NewsFeedViewHolder(ItemFeedBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindClass(FeedModel feedModel) {
        binding.tvFeedTitle.setText(feedModel.getNewsFeedTitle());
        binding.tvFeedSubTitle.setText(feedModel.getNewsFeedSubTitle());
        binding.tvFeedDetails.setText(feedModel.getNewsFeedDetails());
        initializingImageUi(binding, feedModel);
    }

    private void initializingImageUi(ItemFeedBinding binding, final FeedModel feedModel) {
        ImageView ivFeedPhoto = binding.ivFeedPhoto;
        Picasso.get().load(feedModel.getNewsFeedImage()).into(ivFeedPhoto);
        readMoreClickListener(feedModel);
    }

    private void readMoreClickListener(final FeedModel feedModel) {
        binding.tvFeedReadMore.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), FeedDetailsActivity.class);
            intent.putExtra(ConfigurationFile.Constants.DATA, feedModel);
            v.getContext().startActivity(intent);
        });
    }
}
