package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemClassBinding;
import dp.schoolandroid.databinding.ItemFeedBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.activity.FeedDetailsActivity;

public class ClassViewHolder extends RecyclerView.ViewHolder {
    private ItemClassBinding binding;

    public ClassViewHolder(ItemClassBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindClass(FeedModel feedModel) {
        binding.tvClassName.setText(feedModel.getNewsFeedTitle());
        binding.tvClassGrade.setText(feedModel.getNewsFeedSubTitle());
        binding.tvClassNumberOfStudents.setText("22");
    }
}
