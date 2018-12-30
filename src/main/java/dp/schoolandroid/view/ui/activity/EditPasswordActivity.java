package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityEditPasswordBinding;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.viewmodel.EditPasswordViewModel;
import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;
import retrofit2.Response;

public class EditPasswordActivity extends AppCompatActivity {

    ActivityEditPasswordBinding binding;
    EditPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password);
        initializingViewModel();
    }

    private void initializingViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditPasswordViewModel.class);
        binding.setViewModel(viewModel);
    }

    public void sendToChangePassword(View view) {
        if (ValidationUtils.validateTexts(binding.oldPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                &&ValidationUtils.validateTexts(binding.newPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)
                && ValidationUtils.validateTexts(binding.confirmPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
            viewModel.handleChangePasswordTeacher();
            observeTeacherChangeasswordDataViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_password, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void observeTeacherChangeasswordDataViewModel(EditPasswordViewModel viewModel) {
        viewModel.getChangePasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                if (forgetPasswordResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    if (forgetPasswordResponseResponse.body() != null) {
                        Snackbar.make(binding.getRoot(), forgetPasswordResponseResponse.body().getForgetPasswordResponseMessage(), Snackbar.LENGTH_LONG).show();
                        onBackPressed();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), R.string.error_password, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
