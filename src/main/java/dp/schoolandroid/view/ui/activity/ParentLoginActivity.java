package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityParentLoginBinding;
import dp.schoolandroid.viewmodel.ParentLoginActivityViewModel;

public class ParentLoginActivity extends AppCompatActivity {
    ParentLoginActivityViewModel viewModel;
    ActivityParentLoginBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        initializeUi();
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
    }

    private void initializeUi() {
        viewModel = ViewModelProviders.of(this).get(ParentLoginActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_login);
        binding.setViewModel(viewModel);
        binding.forgetPasswordConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForgetPasswordActivity.class);
                intent.putExtra("ACTIVITY_NAME", 2);
                startActivity(intent);
            }
        });
    }

}
