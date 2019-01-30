package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemSessionHistoryBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;
import dp.schoolandroid.service.model.response.teacherresponse.SessionHistoryResponse;
import dp.schoolandroid.view.ui.viewholder.SessionHistoryViewHolder;

public class SessionHistoryRecyclerViewAdapter extends RecyclerView.Adapter<SessionHistoryViewHolder> {
    private ArrayList<SessionHistoryResponseModel> historySessions;
    private SectionTimeModel sectionTimeModel;

    public SessionHistoryRecyclerViewAdapter(ArrayList<SessionHistoryResponseModel> sessionHistoryResponse, SectionTimeModel sectionTimeModel) {
        this.historySessions = sessionHistoryResponse;
        this.sectionTimeModel=sectionTimeModel;
    }

    @NonNull
    @Override
    public SessionHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSessionHistoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_session_history, parent, false);
        return new SessionHistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionHistoryViewHolder holder, int position) {
        holder.bindSession(historySessions.get(position),sectionTimeModel);
    }

    @Override
    public int getItemCount() {
        if (historySessions !=null){
            return historySessions.size();
        }
        return 0;
    }
}
