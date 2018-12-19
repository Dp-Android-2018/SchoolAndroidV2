package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityTeacherLoginBinding;
import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;
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
        viewModel=ViewModelProviders.of(this).get(TeacherLoginActivityViewModel.class);
        binding.setViewModel(viewModel);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
    }
}
