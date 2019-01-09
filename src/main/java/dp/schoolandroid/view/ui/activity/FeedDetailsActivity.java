package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ActivityFeedDetailsBinding;
import dp.schoolandroid.service.model.global.FeedModel;

/*
 * this class is responsible for get and set up Feed Details
 */

public class FeedDetailsActivity extends AppCompatActivity {
    private FeedModel feedModel;
    ActivityFeedDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
        setupToolbar();
    }

    private void setupToolbar() {
        binding.suggestionToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.suggestionToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_details);
        feedModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.DATA);
        initializingUi();
    }

    private void initializingUi() {
        ImageView ivFeedPhoto = binding.ivFeedDetailsPhoto;
        Picasso.get().load(feedModel.getNewsFeedImage()).into(ivFeedPhoto);
        binding.tvFeedDetailsTitle.setText(feedModel.getNewsFeedTitle());
        binding.tvFeedDetailsSubTitle.setText(feedModel.getNewsFeedSubTitle());
        binding.tvFeedDetailsText.setText(feedModel.getNewsFeedDetails());
    }
}
