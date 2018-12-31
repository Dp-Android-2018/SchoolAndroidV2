package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.FragmentPictureGalleryBinding;
import dp.schoolandroid.databinding.FragmentVideosBinding;


public class VideosActivity extends AppCompatActivity {

    FragmentVideosBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.fragment_videos);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
    }

    private void setupToolbar() {
        binding.videosToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.videosToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}
