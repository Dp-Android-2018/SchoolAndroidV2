package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
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
import dp.schoolandroid.databinding.ActivityTeacherAssignmentDetailsBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.QuizHistoryResponseModel;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.response.teacherresponse.QuizHistoryResponse;
import dp.schoolandroid.view.ui.adapter.AssignmentDetailsRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.AssignmentHistoryRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.TeacherAssignmentDetailsActivityViewModel;
import retrofit2.Response;

public class TeacherAssignmentDetailsActivity extends AppCompatActivity {

    ActivityTeacherAssignmentDetailsBinding binding;
    TeacherAssignmentDetailsActivityViewModel viewModel;
    private int assignmentId;
    private SectionTimeModel sectionTimeModel;
    private AssignmentHistoryResponseModel sessionHistoryResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_assignment_details);
        sessionHistoryResponseModel =getIntent().getParcelableExtra(ConfigurationFile.Constants.ASSIGNMENTData);
        assignmentId = Integer.parseInt(sessionHistoryResponseModel.getAssignmentId());
        sectionTimeModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL);
        setupToolbar();
        setupUiwithData();
        setupViewModel();
    }

    private void setupUiwithData() {
        binding.classNameTextView.setText(sectionTimeModel.getClassName());
        binding.studentsCount.setText(sectionTimeModel.getStudentsCount());
    }

    private void setupToolbar() {
        binding.assignmenDetailstsToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.assignmenDetailstsToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupViewModel() {
        if (ValidationUtils.isConnectingToInternet(this)) {
            SharedUtils.getInstance().showProgressDialog(this);
            viewModel = ViewModelProviders.of(this).get(TeacherAssignmentDetailsActivityViewModel.class);
            viewModel.executeGetQuizResponses(assignmentId);
            observeViewModel(viewModel);
        }else {
            Intent intent=new Intent(this,ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void observeViewModel(TeacherAssignmentDetailsActivityViewModel viewModel) {
        viewModel.getData().observe(this, quizHistoryResponseResponse -> {
            if (quizHistoryResponseResponse != null) {
                if (quizHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE ||
                        quizHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_SECOND) {
                    SharedUtils.getInstance().cancelDialog();
                    if (quizHistoryResponseResponse.body() != null) {
                        setupSessionRecyclerView(quizHistoryResponseResponse.body().getQuizHistoryResponseModels());
                    }
                } else if (quizHistoryResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void setupSessionRecyclerView(ArrayList<QuizHistoryResponseModel> quizHistoryResponseModels) {
        AssignmentDetailsRecyclerViewAdapter assignmentHistoryRecyclerViewAdapter = new AssignmentDetailsRecyclerViewAdapter(quizHistoryResponseModels,sessionHistoryResponseModel);
        binding.rvAssignmentStudents.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        binding.rvAssignmentStudents.setAdapter(assignmentHistoryRecyclerViewAdapter);
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
