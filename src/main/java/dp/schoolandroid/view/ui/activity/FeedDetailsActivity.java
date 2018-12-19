package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityFeedDetailsBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.viewmodel.FeedDetailsActivityViewModel;

/*
 * this class is responsible for get Feed Details
 */
public class FeedDetailsActivity extends AppCompatActivity {
    ActivityFeedDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }

    //this function is to inialize Ui
    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_details);
        Gson gson = new Gson();
        FeedModel feedModel = gson.fromJson(getIntent().getStringExtra("DATA"), FeedModel.class);
        FeedDetailsActivityViewModel viewModel = new FeedDetailsActivityViewModel(feedModel);
        ImageView ivFeedPhoto = binding.ivFeedDetailsPhoto;
        Picasso.get().load(feedModel.getImage()).into(ivFeedPhoto);
        binding.setViewModel(viewModel);
    }
}
