package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.LiveData;
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
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityStudentLoginBinding;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.viewmodel.StudentLoginActivityViewModel;
import retrofit2.Response;

/*
 * this class is responsible for student login activity
 * */
public class StudentLoginActivity extends AppCompatActivity {
    ActivityStudentLoginBinding binding;
    StudentLoginActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        initViewModel();
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
    }

    //here initializing the Viewmodel
    private void initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_login);
        viewModel = ViewModelProviders.of(this).get(StudentLoginActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    public void checkDataValidation(View view) {
        if (ValidationUtils.validateTexts(binding.studentPhoneEditText.getText().toString(), ValidationUtils.TYPE_TEXT)
                && ValidationUtils.validateTexts(binding.studentPasswordEditText.getText().toString(), ValidationUtils.TYPE_PASSWORD)) {
            viewModel.handleloginStudent();
            ObserverStudentLoginViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_ssn_or_password, Snackbar.LENGTH_SHORT).show();
        }
    }

    //this function is responsible for observing the coming login data from viewmodel
    public void ObserverStudentLoginViewModel(StudentLoginActivityViewModel viewModel) {
        if (viewModel != null) {
            LiveData<Response<StudentResponse>> studentLoginResponseLiveData = viewModel.getStudentLoginResponseLiveData();
            studentLoginResponseLiveData.observe(this, studentResponseResponse -> {
                if (studentResponseResponse != null) {
                    if (studentResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                        moveToHomeActivity();
                    }
                }
            });
        }
    }
    private void moveToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void forgetPasswordStudentValidation(View view) {
        if (ValidationUtils.validateTexts(binding.studentPhoneEditText.getText().toString(), ValidationUtils.TYPE_PHONE)) {
            viewModel.handleFofgetPasswordStudent();
            ObserverStudentForgetPasswordViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_phone_number, Snackbar.LENGTH_SHORT).show();
        }
    }

    //this function is responsible for observing the coming forget password data from viewmodel
    public void ObserverStudentForgetPasswordViewModel(StudentLoginActivityViewModel viewModel) {
        if (viewModel != null) {
            LiveData<Response<ForgetPasswordResponse>> studentLoginResponseLiveData = viewModel.getForgetPasswordResponseLiveData();
            studentLoginResponseLiveData.observe(this, forgetPasswordResponseResponse -> {
                if (forgetPasswordResponseResponse != null) {
                    if (forgetPasswordResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                        moveToPasswordActivity();
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.please_wait, Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void moveToPasswordActivity() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.putExtra(ConfigurationFile.Constants.ACTIVITY_NUMBER, ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE);
        intent.putExtra(ConfigurationFile.Constants.PHONE_NUMBER, binding.studentPhoneEditText.getText().toString());
        startActivity(intent);
    }
}
