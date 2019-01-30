package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemSessionHistoryBinding;
import dp.schoolandroid.databinding.ItemTeacherAssignmentBinding;
import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;
import dp.schoolandroid.view.ui.viewholder.AssignmentHistoryViewHolder;
import dp.schoolandroid.view.ui.viewholder.SessionHistoryViewHolder;

public class AssignmentHistoryRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentHistoryViewHolder> {
    private ArrayList<AssignmentHistoryResponseModel> historySessions;
    private SectionTimeModel sectionTimeModel;

    public AssignmentHistoryRecyclerViewAdapter(ArrayList<AssignmentHistoryResponseModel> sessionHistoryResponse, SectionTimeModel sectionTimeModel) {
        this.historySessions = sessionHistoryResponse;
        this.sectionTimeModel=sectionTimeModel;
    }

    @NonNull
    @Override
    public AssignmentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTeacherAssignmentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_teacher_assignment, parent, false);
        return new AssignmentHistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentHistoryViewHolder holder, int position) {
        holder.bindAssignment(historySessions.get(position),sectionTimeModel);
    }

    @Override
    public int getItemCount() {
        if (historySessions !=null){
            return historySessions.size();
        }
        return 0;
    }
}
