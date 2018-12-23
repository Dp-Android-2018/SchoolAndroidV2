package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityTeacherLoginBinding;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.NewsFeedRepository;
import dp.schoolandroid.service.repository.remotes.TeacherGetScheduleRepository;
import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;
import retrofit2.Response;

/*
 * this class is responsible for initialize the Teacher Login Activity
 * */
public class TeacherLoginActivity extends AppCompatActivity {
    ActivityTeacherLoginBinding binding;
    TeacherLoginActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_login);
        viewModel = ViewModelProviders.of(this).get(TeacherLoginActivityViewModel.class);
        binding.setViewModel(viewModel);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
    }


    public void teacherLogin(View view) {
        if (ValidationUtils.validateTexts(binding.teacherPhoneEditText.getText().toString(), ValidationUtils.TYPE_PHONE)
                && ValidationUtils.validateTexts(binding.teacherPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
            viewModel.handleloginTeacher();
            observeTeacherLoginDataViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_phone_or_password, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void observeTeacherLoginDataViewModel(TeacherLoginActivityViewModel viewModel) {
        viewModel.getTeacherLoginResponseLiveData().observe(this, teacherResponseResponse -> {
            if (teacherResponseResponse != null) {
                if (teacherResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    moveToHomeActivity();
                    if (teacherResponseResponse.body() != null) {
                        TeacherGetScheduleRepository.getInstance().setBearerToken( teacherResponseResponse.body().getTeacherData().getApiToken());
                        NewsFeedRepository.getInstance().setBearerToken(teacherResponseResponse.body().getTeacherData().getApiToken());
                    }
                } else {
                    Snackbar.make(binding.getRoot(), getString(R.string.error_code) + teacherResponseResponse.code(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void teacherForgetPasswordClicked(View view) {
        if (ValidationUtils.validateTexts(binding.teacherPhoneEditText.getText().toString(), ValidationUtils.TYPE_PHONE)) {
            viewModel.handleForgetPasswordTeacher();
            observeTeacherForgetPasswordDataViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_phone_number, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void observeTeacherForgetPasswordDataViewModel(TeacherLoginActivityViewModel viewModel) {
        viewModel.getForgetPasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                if (forgetPasswordResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    moveToPasswordActivity();
                } else {
                    Snackbar.make(binding.getRoot(), R.string.please_wait, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void moveToPasswordActivity() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.putExtra(ConfigurationFile.Constants.ACTIVITY_NUMBER, ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE);
        intent.putExtra(ConfigurationFile.Constants.PHONE_NUMBER, binding.teacherPhoneEditText.getText().toString());
        startActivity(intent);
    }
}
