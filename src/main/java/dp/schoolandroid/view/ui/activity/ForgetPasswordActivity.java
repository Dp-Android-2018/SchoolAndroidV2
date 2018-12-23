package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityForgetPasswordBinding;
import dp.schoolandroid.viewmodel.ForgetPasswordViewModel;

/*
 * this class is responsible for Forget Password Activity
 * */
public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    ForgetPasswordViewModel viewModel;
    int membershipType;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        initializeUi();
    }

    private void initializeUi() {
        membershipType = getIntent().getIntExtra(ConfigurationFile.Constants.ACTIVITY_NUMBER, 0);
        phoneNumber = getIntent().getStringExtra(ConfigurationFile.Constants.PHONE_NUMBER);
        binding.numberTextView.setText(phoneNumber);
        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);
        viewModel.setType(membershipType);
        initializeViewModel();
    }

    private void initializeViewModel() {
        switch (membershipType) {
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_parent);
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_student);
                break;
        }
        viewModel.setPhoneNumber(phoneNumber);
        binding.setViewModel(viewModel);
    }

    public void checkCode(View view) {
        if (ValidationUtils.validateTexts(binding.codeEditText.getText().toString(), ValidationUtils.TYPE_PHONE)) {
            viewModel.handleCheckCode();
            observeCheckViewModel(viewModel);
        } else {
            Toast.makeText(this, R.string.error_phone_or_password, Toast.LENGTH_SHORT).show();
        }
    }

    private void observeCheckViewModel(ForgetPasswordViewModel viewModel) {
        viewModel.getForgetPasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                if (forgetPasswordResponseResponse.code() == 200) {
                    if (forgetPasswordResponseResponse.body() != null) {
                        startResetPasswordActivity(forgetPasswordResponseResponse.body().getMessage(), membershipType);
                    }
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, getString(R.string.error) + forgetPasswordResponseResponse.code(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startResetPasswordActivity(String apiToken, int type) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        intent.putExtra(ConfigurationFile.Constants.API_TOKEN, apiToken);
        intent.putExtra(ConfigurationFile.Constants.TYPE, type);
        startActivity(intent);
    }
}
