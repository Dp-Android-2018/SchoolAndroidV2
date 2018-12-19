package dp.schoolandroid.view.ui.activity;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityMainBinding;
import dp.schoolandroid.viewmodel.MainActiviyViewModel;

public class MainActivity extends AppCompatActivity {
    private final int TEACHER_SELECTOR = 1, STUDENT_SELECTOR = 2, PARENT_SELECTOR = 3;
    private ActivityMainBinding mBinding = null;
    private int mSelectedTab = -1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        handleSelectionTypeEvent();
    }

    private void bindView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActiviyViewModel dataviewModel = ViewModelProviders.of(this).get(MainActiviyViewModel.class);
        mBinding.setMainActivityData(dataviewModel);
    }

    public void handleSelectionEvent(View view) {
        if (view.getId() == R.id.iv_main_student_image) {
            mSelectedTab = STUDENT_SELECTOR;
        } else if (view.getId() == R.id.iv_main_parent_image) {
            mSelectedTab = PARENT_SELECTOR;
        } else if (view.getId() == R.id.iv_main_teacher_image) {
            mSelectedTab = TEACHER_SELECTOR;
        }
        handleTextLabelColor();
        handleImageResource();
        mBinding.btnChoose.setBackgroundResource(R.drawable.btn_background_white);
    }

    private void handleSelectionTypeEvent() {
        mBinding.btnChoose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (mSelectedTab != -1) {
                    startNextActivity();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startNextActivity() {
        Intent loginIntent = null;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        switch (mSelectedTab) {
            case TEACHER_SELECTOR:
                loginIntent = new Intent(MainActivity.this, TeacherLoginActivity.class);
                break;
            case STUDENT_SELECTOR:
                loginIntent = new Intent(MainActivity.this, StudentLoginActivity.class);
                break;
            case PARENT_SELECTOR:
                loginIntent = new Intent(MainActivity.this, ParentLoginActivity.class);
                break;
            default:
                loginIntent = null;
        }
        if (loginIntent != null) {
            startActivity(loginIntent, options.toBundle());
        }
    }

    private void handleTextLabelColor() {
        mBinding.tvMainStudentLabel.setTextColor(mSelectedTab == STUDENT_SELECTOR ? getTextColor(true) : getTextColor(false));
        mBinding.tvMainParentLabel.setTextColor(mSelectedTab == PARENT_SELECTOR ? getTextColor(true) : getTextColor(false));
        mBinding.tvMainTeacherLabel.setTextColor(mSelectedTab == TEACHER_SELECTOR ? getTextColor(true) : getTextColor(false));
    }

    private void handleImageResource() {
        mBinding.ivMainTeacherImage.setImageResource(mSelectedTab == TEACHER_SELECTOR ? R.drawable.img_un_checked_teacher
                : R.drawable.img_checked_teacher);
        mBinding.ivMainStudentImage.setImageResource(mSelectedTab == STUDENT_SELECTOR ? R.drawable.img_un_checked_student :
                R.drawable.img_checked_student);
        mBinding.ivMainParentImage.setImageResource(mSelectedTab == PARENT_SELECTOR ? R.drawable.img_un_checked_parent :
                R.drawable.img_checked_parent);
    }

    private int getTextColor(boolean isActive) {
        if (isActive) {
            return ContextCompat.getColor(getBaseContext(), R.color.colorWhite);
        }
        return ContextCompat.getColor(getBaseContext(), R.color.colorGray);
    }
}
