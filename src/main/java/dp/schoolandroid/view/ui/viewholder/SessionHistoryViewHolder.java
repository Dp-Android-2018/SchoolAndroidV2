package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemSessionHistoryBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;
import dp.schoolandroid.view.ui.activity.TeacherSessionActivity;

public class SessionHistoryViewHolder extends RecyclerView.ViewHolder {

    private ItemSessionHistoryBinding binding;
    private SessionHistoryResponseModel sessionHistoryResponseModel;
    private SectionTimeModel sectionTimeModel;

    public SessionHistoryViewHolder(ItemSessionHistoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindSession(SessionHistoryResponseModel sessionHistoryResponseModel, SectionTimeModel sectionTimeModel) {
        this.sectionTimeModel=sectionTimeModel;
        this.sessionHistoryResponseModel = sessionHistoryResponseModel;
        binding.sessionDateTextView.setText(sessionHistoryResponseModel.getSessionDate());
        binding.sessionteacherNameTextView.setText(sessionHistoryResponseModel.getSessionTeacherName());
        itemClickedMethod();
    }

    private void itemClickedMethod() {
        binding.getRoot().setOnClickListener(v -> {
            Intent intent=new Intent(binding.getRoot().getContext(),TeacherSessionActivity.class);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTYPE, ConfigurationFile.Constants.HISTORYSESSION);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL, sectionTimeModel);
            intent.putExtra(ConfigurationFile.Constants.SESSIONID, sessionHistoryResponseModel.getSessionId());
            binding.getRoot().getContext().startActivity(intent);
        });
    }
}
