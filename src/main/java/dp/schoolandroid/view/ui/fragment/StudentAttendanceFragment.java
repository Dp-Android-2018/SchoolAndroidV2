package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.application.MyApp;
import dp.schoolandroid.databinding.FragmentStudentAttendanceBinding;
import dp.schoolandroid.service.model.global.AbsentStudentModel;
import dp.schoolandroid.service.model.global.SessionResponseDataModel;
import dp.schoolandroid.service.model.global.StudentsInfoModel;
import dp.schoolandroid.service.model.request.TeachingSessionRequest;
import dp.schoolandroid.service.model.response.SessionResponseData;
import dp.schoolandroid.view.ui.activity.ConnectionErrorActivity;
import dp.schoolandroid.view.ui.activity.MainActivity;
import dp.schoolandroid.view.ui.adapter.StudentsRecyclerViewAdapter;
import dp.schoolandroid.view.ui.callback.StudentAbsence;
import dp.schoolandroid.viewmodel.StudentAttendanceViewModel;
import retrofit2.Response;

public class StudentAttendanceFragment extends Fragment {

    FragmentStudentAttendanceBinding binding;
    StudentAttendanceViewModel viewModel;
    private SessionResponseDataModel studentsInfo;
    private int startedSessionId;
    private String sessionType;

    @Inject
    public StudentAttendanceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_attendance, container, false);
        sessionType = Objects.requireNonNull(getArguments()).getString(ConfigurationFile.Constants.SESSIONTYPE);
        startedSessionId = getArguments().getInt(ConfigurationFile.Constants.SESSIONID);
        SharedUtils.getInstance().showProgressDialog(getActivity());
        setUpViewModel();
        ((MyApp) (Objects.requireNonNull(getActivity()).getApplicationContext())).clearAbsentStudents();
        ((MyApp) (getActivity().getApplicationContext())).clearStudentOfDay();
        setSaveButtonClickFunction();
        return binding.getRoot();
    }

    private void setUpViewModel() {
        if (ValidationUtils.isConnectingToInternet(Objects.requireNonNull(getContext()))) {
            viewModel = ViewModelProviders.of(this).get(StudentAttendanceViewModel.class);
            viewModel.executeGetDataforSession(startedSessionId);
            observeViewModel(viewModel);
        } else {
            Intent intent = new Intent(getContext(), ConnectionErrorActivity.class);
            startActivity(intent);
        }

    }

    private void observeViewModel(StudentAttendanceViewModel viewModel) {
        viewModel.getData().observe(this, sessionResponseDataResponse -> {
            if (sessionResponseDataResponse != null) {
                if (sessionResponseDataResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (sessionResponseDataResponse.body() != null) {
                        setupStudentsRecyclerView(sessionResponseDataResponse.body().getSessionResponseModel());
                    }
                } else if (sessionResponseDataResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void setupStudentsRecyclerView(SessionResponseDataModel studentsInfo) {
        SharedUtils.getInstance().setStudentOfDayIsSelected(false);
        this.studentsInfo = studentsInfo;
        StudentsRecyclerViewAdapter studentsRecyclerViewAdapter = new StudentsRecyclerViewAdapter(studentsInfo);
        binding.rvStudents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvStudents);
        binding.rvStudents.setAdapter(studentsRecyclerViewAdapter);
    }

    private void setSaveButtonClickFunction() {
        binding.saveButton.setOnClickListener(v -> {
            SharedUtils.getInstance().showProgressDialog(getActivity());
            viewModel.executeUpdateDataforSession(startedSessionId);
            obsereUpdateDataforSessionViewModel();
        });
    }

    private void obsereUpdateDataforSessionViewModel() {
        viewModel.getData().observe(this, sessionResponseDataResponse -> {
            if (sessionResponseDataResponse != null) {
                if (sessionResponseDataResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (sessionResponseDataResponse.body() != null) {
                        Snackbar.make(binding.getRoot(), sessionResponseDataResponse.body().getSessionResponseMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                } else if (sessionResponseDataResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        customUtils.clearSharedPref();
    }


    @Override
    public void onPause() {
        super.onPause();
        ((MyApp) (Objects.requireNonNull(getActivity()).getApplicationContext())).clearAbsentStudents();
        ((MyApp) (getActivity().getApplicationContext())).clearStudentOfDay();
    }
}
