package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityChatBinding;

/*
 * this class is responsible for get and set up Chat Activity
 */
public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
    }

    private void setupToolbar() {
        binding.chatToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.chatToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}
