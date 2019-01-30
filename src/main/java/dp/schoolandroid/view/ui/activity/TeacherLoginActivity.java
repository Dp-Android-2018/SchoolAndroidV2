package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityTeacherLoginBinding;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;

/*
 * this class is responsible for get and set up teacher Login
 * make actions when clicking on login
 * make actions when clicking on forget password
 */
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
        if (ValidationUtils.isConnectingToInternet(this)) {
            if (ValidationUtils.validateTexts(binding.teacherPhoneEditText.getText().toString(), ValidationUtils.TYPE_PHONE)
                    && ValidationUtils.validateTexts(binding.teacherPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
                SharedUtils.getInstance().showProgressDialog(this);
                viewModel.handleloginTeacher();
                observeTeacherLoginDataViewModel(viewModel);
            } else {
                Snackbar.make(binding.getRoot(), R.string.error_phone_or_password, Snackbar.LENGTH_SHORT).show();
            }
        }else {
            Intent intent=new Intent(this,ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void observeTeacherLoginDataViewModel(TeacherLoginActivityViewModel viewModel) {
        viewModel.getTeacherLoginResponseLiveData().observe(this, teacherResponseResponse -> {
            if (teacherResponseResponse != null) {
                SharedUtils.getInstance().cancelDialog();
                if (teacherResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    moveToHomeActivity();
                    if (teacherResponseResponse.body() != null) {
                        saveTeacherDataToSharedPreferences(teacherResponseResponse.body());
                    }
                } else {
                    Snackbar.make(binding.getRoot(), R.string.error_phone_or_password, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveTeacherDataToSharedPreferences(TeacherResponse body) {
        CustomUtils customUtils = new CustomUtils(getApplication());
        customUtils.clearSharedPref();
        customUtils.saveMemberTypeToPrefs(ConfigurationFile.Constants.MEMBER_Key,ConfigurationFile.Constants.TEACHER_Key_VALUE);
        customUtils.saveTeacherDataToPrefs(body);
    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(this, TeacherHomeActivity.class);
        intent.putExtra(ConfigurationFile.Constants.MEMBER_Key,ConfigurationFile.Constants.TEACHER_Key_VALUE);
        startActivity(intent);
        finish();
    }

    public void teacherForgetPasswordClicked(View view) {
        if (ValidationUtils.validateTexts(binding.teacherPhoneEditText.getText().toString(), ValidationUtils.TYPE_PHONE)) {
            SharedUtils.getInstance().showProgressDialog(this);
            viewModel.handleForgetPasswordTeacher();
            observeTeacherForgetPasswordDataViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_phone_number, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void observeTeacherForgetPasswordDataViewModel(TeacherLoginActivityViewModel viewModel) {
        viewModel.getForgetPasswordResponseLiveData().observe(this, forgetPasswordResponseResponse -> {
            if (forgetPasswordResponseResponse != null) {
                SharedUtils.getInstance().cancelDialog();
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
        finish();
    }
}
