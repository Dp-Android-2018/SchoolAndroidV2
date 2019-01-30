package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemClassStudentAssignmentBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.QuizHistoryResponseModel;
import dp.schoolandroid.view.ui.viewholder.AssignmentDetailsViewHolder;

public class AssignmentDetailsRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentDetailsViewHolder> {
    private ArrayList<QuizHistoryResponseModel> quizHistoryResponseModels;
    private AssignmentHistoryResponseModel assignmentHistoryResponseModel;

    public AssignmentDetailsRecyclerViewAdapter(ArrayList<QuizHistoryResponseModel> sessionHistoryResponse, AssignmentHistoryResponseModel sectionTimeModel) {
        this.quizHistoryResponseModels = sessionHistoryResponse;
        this.assignmentHistoryResponseModel =sectionTimeModel;
    }

    @NonNull
    @Override
    public AssignmentDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemClassStudentAssignmentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_class_student_assignment, parent, false);
        return new AssignmentDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentDetailsViewHolder holder, int position) {
        holder.bindAssignment(quizHistoryResponseModels.get(position), assignmentHistoryResponseModel);
    }

    @Override
    public int getItemCount() {
        if (quizHistoryResponseModels !=null){
            return quizHistoryResponseModels.size();
        }
        return 0;
    }
}
