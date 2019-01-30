package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityEditPasswordBinding;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.viewmodel.EditPasswordViewModel;
import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;
import retrofit2.Response;

public class EditPasswordActivity extends AppCompatActivity {

    ActivityEditPasswordBinding binding;
    EditPasswordViewModel viewModel;
    private String memberType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password);
        memberType = getIntent().getStringExtra(ConfigurationFile.Constants.MEMBER_Key);
        setupToolbar();
        initializingViewModel();
    }

    private void setupToolbar() {
        binding.profileToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.profileToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void initializingViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditPasswordViewModel.class);
        binding.setViewModel(viewModel);
    }

    public void sendToChangePassword(View view) {
        if (memberType.equals(ConfigurationFile.Constants.TEACHER_Key_VALUE)) {
            changePasswordTeacher();
        } else if (memberType.equals(ConfigurationFile.Constants.STUDENT_Key_VALUE)) {
            changePasswordStudent();
        }
    }

    private void changePasswordTeacher() {
        if (ValidationUtils.isConnectingToInternet(this)) {
            if (ValidationUtils.validateTexts(binding.oldPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                    && ValidationUtils.validateTexts(binding.newPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                    && ValidationUtils.validateTexts(binding.confirmPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
                SharedUtils.getInstance().showProgressDialog(this);
                viewModel.handleChangePasswordTeacher();
                observeChangeasswordDataViewModel(viewModel);
            } else {
                Snackbar.make(binding.getRoot(), R.string.error_password, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(this, ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void changePasswordStudent() {
        if (ValidationUtils.isConnectingToInternet(this)) {
            if (ValidationUtils.validateTexts(binding.oldPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                    && ValidationUtils.validateTexts(binding.newPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                    && ValidationUtils.validateTexts(binding.confirmPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
                SharedUtils.getInstance().showProgressDialog(this);
                viewModel.handleChangePasswordStudent();
                observeChangeasswordDataViewModel(viewModel);
            } else {
                Snackbar.make(binding.getRoot(), R.string.error_password, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Snackbar.make(binding.getRoot(), R.string.there_is_no_internet, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void observeChangeasswordDataViewModel(EditPasswordViewModel viewModel) {
        viewModel.getChangePasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                SharedUtils.getInstance().cancelDialog();
                if (forgetPasswordResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    if (forgetPasswordResponseResponse.body() != null) {
                        new Handler().postDelayed(() ->
                                        Snackbar.make(binding.getRoot(), forgetPasswordResponseResponse.body().getForgetPasswordResponseMessage(), Snackbar.LENGTH_LONG).show()
                                , 2000);
                        onBackPressed();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), R.string.error_password, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
