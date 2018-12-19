package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityStudentLoginBinding;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.viewmodel.StudentLoginActivityViewModel;

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
        ObserverViewModel(viewModel);
    }

    //this function is responsible for observing the coming data from viewmodel
    public void ObserverViewModel(StudentLoginActivityViewModel viewModel) {
        if (viewModel != null) {
            LiveData<StudentResponse> studentLoginResponseLiveData = viewModel.getStudentLoginResponseLiveData();
            studentLoginResponseLiveData.observe(this, new Observer<StudentResponse>() {
                @Override
                public void onChanged(@Nullable StudentResponse studentResponse) {

                }
            });
        } else {
            Toast.makeText(this, "Null Value ", Toast.LENGTH_LONG).show();
        }
    }
}
