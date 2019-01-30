package dp.schoolandroid.view.ui.fragment;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.FragmentStudentQuizBinding;
import dp.schoolandroid.view.ui.activity.ConnectionErrorActivity;
import dp.schoolandroid.view.ui.activity.MainActivity;
import dp.schoolandroid.viewmodel.StudentQuizViewModel;

import static android.app.Activity.RESULT_OK;


public class StudentQuizFragment extends Fragment {

    FragmentStudentQuizBinding binding;
    StudentQuizViewModel viewModel;
    private static final int PICK_REQUEST = 234;
    private Uri fileToUploadPath;
    private ProgressDialog progressDialog;
    private StorageReference storageRef;
    private StorageReference riversRef;
    private UploadTask uploadTask;
    int startedSessionId;
    private URL uploadedFileUrl;

    @Inject
    public StudentQuizFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_quiz, container, false);
        startedSessionId = Objects.requireNonNull(getArguments()).getInt(ConfigurationFile.Constants.SESSIONID);
        viewModel = ViewModelProviders.of(this).get(StudentQuizViewModel.class);
        setClickListenerOnButtonSend();
        setClickListenerOnButtonUpload();
        return binding.getRoot();
    }

    private void setClickListenerOnButtonUpload() {
        binding.btnUpload.setOnClickListener(v -> {
            showFileChooser();
        });
    }

    private void showFileChooser() {
        startActivityForResult(Intent.createChooser(getFileChooserIntent(), getString(R.string.select_file)), PICK_REQUEST);
    }

    private Intent getFileChooserIntent() {
        String[] mimeTypes = {"image/*", "application/pdf", "docx/*"};

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            StringBuilder mimeTypesStr = new StringBuilder();

            for (String mimeType : mimeTypes) {
                mimeTypesStr.append(mimeType).append("|");
            }

            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }

        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileToUploadPath = data.getData();
            uploadFile();
        }
    }

    //this method will upload the file
    private void uploadFile() {
        if (fileToUploadPath != null) {
            storageRef = FirebaseStorage.getInstance().getReference();
            initializeProgressDialog();
            putFileToStorageReference();
        } else {
            Snackbar.make(binding.getRoot(), R.string.there_is_no_file, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void putFileToStorageReference() {
        riversRef = storageRef.child(ConfigurationFile.Constants.QUIZZES_DIRECTORY_NAME);
        riversRef.putFile(fileToUploadPath)
                .addOnSuccessListener(taskSnapshot -> {
                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(uri -> {
                        binding.viewDone.setVisibility(View.VISIBLE);
                        uploadedFileUrl = null;
                        try {
                            uploadedFileUrl = new URL(uri.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    });
                    progressDialog.dismiss();
                    Snackbar.make(binding.getRoot(), R.string.file_uploaded, Snackbar.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    progressDialog.dismiss();
                    Snackbar.make(binding.getRoot(), exception.getMessage(), Snackbar.LENGTH_SHORT).show();
                })
                .addOnProgressListener(taskSnapshot -> {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage(getString(R.string.uploaded) + ((int) progress) + getString(R.string.percentage));
                });
    }

    private void initializeProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(getString(R.string.uploading));
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", (dialog, which) -> {
            if (riversRef != null) {
                uploadTask = riversRef.getActiveUploadTasks().get(0);
                if (uploadTask != null) {
                    uploadTask.cancel();
                    progressDialog.dismiss();
                    Snackbar.make(binding.getRoot(), "Upload cancelled sussessfully :(", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        progressDialog.show();
    }

    private void setClickListenerOnButtonSend() {
        binding.btnSend.setOnClickListener(v -> {
            if (ValidationUtils.isConnectingToInternet(Objects.requireNonNull(getContext()))) {
                if (!TextUtils.isEmpty(binding.quizTitleEditText.getText().toString())
                        && !TextUtils.isEmpty(binding.quizDescribtionEditText.getText().toString())
                        && !TextUtils.isEmpty(binding.gradeEditText.getText().toString())
                        && (uploadedFileUrl != null)) {
                    SharedUtils.getInstance().showProgressDialog(getContext());
                    viewModel.executEcreateNewQuiz(startedSessionId, binding.quizTitleEditText.getText().toString(), binding.quizDescribtionEditText.getText().toString()
                            , Integer.parseInt(binding.gradeEditText.getText().toString()), uploadedFileUrl);
                    observeViewModel(viewModel);
                } else {
                    Snackbar.make(binding.getRoot(), R.string.please_full_all_data, Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Intent intent = new Intent(getContext(), ConnectionErrorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void observeViewModel(StudentQuizViewModel viewModel) {
        viewModel.getData().observe(this, quizRequestResponse -> {
            if (quizRequestResponse != null) {
                SharedUtils.getInstance().cancelDialog();
                if (quizRequestResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE
                        || quizRequestResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_SECOND) {
                    if (quizRequestResponse.body() != null) {
                        Snackbar.make(binding.getRoot(), "title :" + quizRequestResponse.body().getQuizTitle(), Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.no_classes, Snackbar.LENGTH_SHORT).show();
                    }
                } else if (quizRequestResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        customUtils.clearSharedPref();
    }

}
