package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemTeacherAssignmentBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.view.ui.activity.TeacherAssignmentDetailsActivity;

public class AssignmentHistoryViewHolder extends RecyclerView.ViewHolder {

    private ItemTeacherAssignmentBinding binding;
    private AssignmentHistoryResponseModel sessionHistoryResponseModel;
    private SectionTimeModel sectionTimeModel;

    public AssignmentHistoryViewHolder(ItemTeacherAssignmentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindAssignment(AssignmentHistoryResponseModel sessionHistoryResponseModel, SectionTimeModel sectionTimeModel) {
        this.sectionTimeModel=sectionTimeModel;
        this.sessionHistoryResponseModel = sessionHistoryResponseModel;
        binding.quizTitleTextView.setText(sessionHistoryResponseModel.getAssignmentTitle());
        binding.describtionTextView.setText(sessionHistoryResponseModel.getAssignmentGrade());
        itemClickedMethod();
    }

    private void itemClickedMethod() {
        binding.getRoot().setOnClickListener(v -> {
            Intent intent=new Intent(binding.getRoot().getContext(),TeacherAssignmentDetailsActivity.class);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL, sectionTimeModel);
            intent.putExtra(ConfigurationFile.Constants.ASSIGNMENTData, sessionHistoryResponseModel);
            binding.getRoot().getContext().startActivity(intent);
        });
    }
}
