package dp.schoolandroid.view.ui.activity;

import android.app.DownloadManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityStudentGradeBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.QuizHistoryResponseModel;
import dp.schoolandroid.viewmodel.StudentGradeViewModel;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class StudentGradeActivity extends AppCompatActivity {

    ActivityStudentGradeBinding binding;
    StudentGradeViewModel viewModel;
    private QuizHistoryResponseModel quizHistoryResponseModel;
    private AssignmentHistoryResponseModel sessionHistoryResponseModel;
    private int points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_grade);
        viewModel = ViewModelProviders.of(this).get(StudentGradeViewModel.class);
        sessionHistoryResponseModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.ASSIGNMENTData);
        quizHistoryResponseModel = new Gson().fromJson(getIntent().getStringExtra(ConfigurationFile.Constants.QUIZDETAILS), QuizHistoryResponseModel.class);
        binding.dialogNumberPicker.setMinValue(0);// restricted number to minimum value i.e 1
        binding.dialogNumberPicker.setMaxValue(Integer.parseInt(sessionHistoryResponseModel.getAssignmentGrade()));// restricked number to maximum value i.e. 31
        binding.dialogNumberPicker.setWrapSelectorWheel(true);
        setupToolbar();
        initializeUi();
        makeClickingActionOnBtnDownload();
        makeClickingActionOnBtnSend();
    }

    private void initializeUi() {
        binding.studentNameTextView.setText(quizHistoryResponseModel.getStudentDataModel().getStudentName());
        binding.gradeTextView.setText(sessionHistoryResponseModel.getAssignmentGrade());
        ImageView ivGalleryPhoto = binding.ivStudentPhoto;
        Picasso.get().load(quizHistoryResponseModel.getStudentDataModel().getStudentImage()).into(ivGalleryPhoto);
        makeIncreaseAndDecreaseClicks();

    }

    private void makeIncreaseAndDecreaseClicks() {


        binding.decreaseImageView.setOnClickListener(v -> {
            points = binding.dialogNumberPicker.getValue();
            if (points < Integer.parseInt(sessionHistoryResponseModel.getAssignmentGrade())) {
                points++;
                binding.dialogNumberPicker.setValue(points);
            }
        });

        binding.increaseImageView.setOnClickListener(v -> {
            points = binding.dialogNumberPicker.getValue();
            if (points > 0) {
                points--;
                binding.dialogNumberPicker.setValue(points);
            }
        });
    }

    private void setupToolbar() {
        binding.studentGradeToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.studentGradeToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void makeClickingActionOnBtnDownload() {

        binding.buttonFileDownload.setOnClickListener(v -> {
            if (quizHistoryResponseModel.getAttachmentUrl() != null) {
                downloadFile(this, DIRECTORY_DOWNLOADS, quizHistoryResponseModel.getAttachmentUrl());
            }
        });
    }

    public void downloadFile(Context context, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, uri.getPath());

        if (downloadmanager != null) {
            downloadmanager.enqueue(request);
        }
    }

    private void makeClickingActionOnBtnSend() {
        binding.btnSend.setOnClickListener(v -> {
            if (ValidationUtils.isConnectingToInternet(this)) {
                SharedUtils.getInstance().showProgressDialog(this);
                viewModel.executeGivePointsToQuizResponse(Integer.parseInt(sessionHistoryResponseModel.getAssignmentId())
                        , quizHistoryResponseModel.getQuizResponseId(), binding.dialogNumberPicker.getValue());
                observeViewModel();
            } else {
                Snackbar.make(binding.getRoot(), R.string.there_is_no_internet, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void observeViewModel() {
        viewModel.getData().observe(this, sessionResponseDataResponse -> {
            if (sessionResponseDataResponse != null) {
                SharedUtils.getInstance().cancelDialog();
                if (sessionResponseDataResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE
                        || sessionResponseDataResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_SECOND) {
                    if (sessionResponseDataResponse.body() != null) {
                        Snackbar.make(binding.getRoot(), sessionResponseDataResponse.body().getSessionResponseMessage(), Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.no_classes, Snackbar.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(getApplication());
        customUtils.clearSharedPref();
    }
}
