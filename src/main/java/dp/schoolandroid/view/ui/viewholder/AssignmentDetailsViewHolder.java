package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemClassStudentAssignmentBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.QuizHistoryResponseModel;
import dp.schoolandroid.view.ui.activity.StudentGradeActivity;

public class AssignmentDetailsViewHolder extends RecyclerView.ViewHolder {

    private ItemClassStudentAssignmentBinding binding;
    private QuizHistoryResponseModel quizHistoryResponseModel;
    private AssignmentHistoryResponseModel assignmentHistoryResponseModel;

    public AssignmentDetailsViewHolder(ItemClassStudentAssignmentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindAssignment(QuizHistoryResponseModel quizHistoryResponseModel, AssignmentHistoryResponseModel assignmentHistoryResponseModel) {
        this.assignmentHistoryResponseModel = assignmentHistoryResponseModel;
        this.quizHistoryResponseModel = quizHistoryResponseModel;
        binding.tvAssignmentStudentName.setText(quizHistoryResponseModel.getStudentDataModel().getStudentName());
        ImageView ivGalleryPhoto = binding.ivAssignmentStudentPhoto;
        Picasso.get().load(quizHistoryResponseModel.getStudentDataModel().getStudentImage()).into(ivGalleryPhoto);
        binding.tvAssignmentState.setChecked(quizHistoryResponseModel.isAwarded());
        itemClickedMethod();
    }

    private void itemClickedMethod() {
        binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), StudentGradeActivity.class);
            intent.putExtra(ConfigurationFile.Constants.QUIZDETAILS, new Gson().toJson(quizHistoryResponseModel));
            intent.putExtra(ConfigurationFile.Constants.ASSIGNMENTData, assignmentHistoryResponseModel);
            binding.getRoot().getContext().startActivity(intent);
        });
    }
}
