package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityResetPasswordBinding;
import dp.schoolandroid.viewmodel.ResetPasswordActivityViewModel;

/*
 * this class is responsible for initialize reset password activity
 * observing the coming live data from viewModel
 * opening the login activity for the membership after succes resetting password
 * */

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    ResetPasswordActivityViewModel viewModel;
    int membershipType;
    String apiToken;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        phoneNumber = getIntent().getStringExtra(ConfigurationFile.Constants.PHONE_NUMBER);
        viewModel = ViewModelProviders.of(this).get(ResetPasswordActivityViewModel.class);
        apiToken = getIntent().getStringExtra(ConfigurationFile.Constants.API_TOKEN);
        membershipType = getIntent().getIntExtra(ConfigurationFile.Constants.TYPE, 0);
        binding.setViewModel(viewModel);
    }

    public void confrimPassword(View view) {
        if (ValidationUtils.validateTexts(binding.newPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                && ValidationUtils.validateTexts(binding.confirmNewPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
            if (binding.newPasswordEditText.getText().toString().equals(binding.confirmNewPasswordEditText.getText().toString())) {
                viewModel.handleConfirmPassword(membershipType, apiToken, phoneNumber);
                observeCheckViewModel(viewModel);
            } else {
                Snackbar.make(binding.getRoot(), R.string.password_not_match, Toast.LENGTH_SHORT).show();
            }
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_password, Toast.LENGTH_SHORT).show();
        }
    }

    private void observeCheckViewModel(ResetPasswordActivityViewModel viewModel) {
        viewModel.getResetPasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                if (forgetPasswordResponseResponse.code() == 200) {
                    if (forgetPasswordResponseResponse.body() != null) {
                        moveToLoginActivity();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), getString(R.string.error) + forgetPasswordResponseResponse.code(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void moveToLoginActivity() {
        switch (membershipType) {
            case ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE:
                openIntent(TeacherLoginActivity.class);
                break;
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                openIntent(ParentLoginActivity.class);
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                openIntent(StudentLoginActivity.class);
                break;
        }
    }

    private void openIntent(Class activityClass) {
        Intent intent = new Intent(ResetPasswordActivity.this, activityClass);
        startActivity(intent);
        finish();
    }
}
