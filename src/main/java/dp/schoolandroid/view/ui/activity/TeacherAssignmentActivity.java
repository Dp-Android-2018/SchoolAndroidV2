package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityTeacherAssignmentBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.response.teacherresponse.AssignmentHistoryResponse;
import dp.schoolandroid.view.ui.adapter.AssignmentHistoryRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.SessionHistoryRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.TeacherAssignmentActivityViewModel;
import retrofit2.Response;

public class TeacherAssignmentActivity extends AppCompatActivity {

    ActivityTeacherAssignmentBinding binding;
    TeacherAssignmentActivityViewModel viewModel;
    private SectionTimeModel sectionTimeModel;
    private int sessionId;
    private String sessionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_assignment);
        sectionTimeModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL);
        sessionId = getIntent().getIntExtra(ConfigurationFile.Constants.SESSIONID, 0);
        setupToolbar();
        setupViewModel();
    }

    private void setupToolbar() {
        binding.assignmentsToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.assignmentsToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupViewModel() {
        if (ValidationUtils.isConnectingToInternet(this)) {
            SharedUtils.getInstance().showProgressDialog(this);
            viewModel = ViewModelProviders.of(this).get(TeacherAssignmentActivityViewModel.class);
            viewModel.executeListQuizzesHistory(sessionId);
            observeViewModel(viewModel);
        }else {
            Intent intent=new Intent(this,ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void observeViewModel(TeacherAssignmentActivityViewModel viewModel) {
        viewModel.getData().observe(this, assignmentHistoryResponseResponse -> {
            if (assignmentHistoryResponseResponse != null) {
                if (assignmentHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE||
                        assignmentHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_SECOND) {
                    SharedUtils.getInstance().cancelDialog();
                    if (assignmentHistoryResponseResponse.body() != null) {
                        setupSessionRecyclerView(assignmentHistoryResponseResponse.body().getAssignmentHistoryResponseModel());
                    }
                } else if (assignmentHistoryResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void setupSessionRecyclerView(ArrayList<AssignmentHistoryResponseModel> assignmentHistoryResponseModel) {
        AssignmentHistoryRecyclerViewAdapter assignmentHistoryRecyclerViewAdapter = new AssignmentHistoryRecyclerViewAdapter(assignmentHistoryResponseModel,sectionTimeModel);
        binding.rvAssignments.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        binding.rvAssignments.setAdapter(assignmentHistoryRecyclerViewAdapter);
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(this.getApplication());
        customUtils.clearSharedPref();
    }
}
