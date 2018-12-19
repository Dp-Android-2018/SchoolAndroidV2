package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityResetPasswordBinding;
import dp.schoolandroid.viewmodel.ResetPasswordActivityViewModel;

/*
 * this class is responsible for initialize reset password activity
 * */
public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    ResetPasswordActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        viewModel = ViewModelProviders.of(this).get(ResetPasswordActivityViewModel.class);
        String apiToken = getIntent().getStringExtra("APITOKEN");
        int type = getIntent().getIntExtra("TYPE", 0);
        viewModel.setApiToken(apiToken);
        viewModel.setType(type);
        binding.setViewModel(viewModel);
    }
}
