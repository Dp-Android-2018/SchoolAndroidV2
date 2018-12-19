package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityChatBinding;
import dp.schoolandroid.viewmodel.ChatActivityViewModel;
/*
* this class is responsible for chat activity
*/
public class ChatActivity extends AppCompatActivity {
    ChatActivityViewModel viewModel;
    ActivityChatBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        viewModel = ViewModelProviders.of(this).get(ChatActivityViewModel.class);
        binding.setViewModel(viewModel);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
    }
}
