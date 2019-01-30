package dp.schoolandroid.view.ui.viewholder;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ItemScheduleBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.view.ui.activity.TeacherSessionActivity;
import dp.schoolandroid.view.ui.activity.SessionhistoryActivity;

public class DayViewHolder extends RecyclerView.ViewHolder {
    private ItemScheduleBinding binding;
    private SectionTimeModel sectionTimeModel;
    private AlertDialog dialog;

    public DayViewHolder(ItemScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindDay(SectionTimeModel sectionTimeModel) {
        this.sectionTimeModel = sectionTimeModel;
        binding.tvScheduleClassName.setText(sectionTimeModel.getClassName());
        binding.tvScheduleClassGrade.setText(sectionTimeModel.getGrade());
        binding.tvScheduleClassNumberOfStudents.setText(sectionTimeModel.getStudentsCount());
        binding.tvItemToFrom.setText(sectionTimeModel.getTo());
        binding.tvItemScheduleFrom.setText(sectionTimeModel.getFrom());
        itemClickedMethod();
    }

    private void itemClickedMethod() {
        binding.getRoot().setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(binding.getRoot().getContext());
            LayoutInflater factory = LayoutInflater.from(binding.getRoot().getContext());
            final View view = factory.inflate(R.layout.session_dialog_layout, null);
            TextView classNameTextView = (TextView) view.findViewById(R.id.classNameTextView);
            classNameTextView.setText(sectionTimeModel.getClassName());
            alertDialog.setView(view);
            alertDialog.setCancelable(true);
            dialog = alertDialog.create();
            dialog.show();
            chooseItemMethod(view);
        });
    }

    private void chooseItemMethod(View view) {
        ConstraintLayout startNewSessionConstraintLayout = (ConstraintLayout) view.findViewById(R.id.startNewSessionConstraintLayout);
        ConstraintLayout historySessionConstraintLayout = (ConstraintLayout) view.findViewById(R.id.sessionHistoryConstraintLayout);
        startNewSessionConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), TeacherSessionActivity.class);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL, sectionTimeModel);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTYPE, ConfigurationFile.Constants.NEWSESSION);
            binding.getRoot().getContext().startActivity(intent);
            dialog.cancel();
        });

        historySessionConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), SessionhistoryActivity.class);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL, sectionTimeModel);
            binding.getRoot().getContext().startActivity(intent);
            dialog.cancel();
        });
    }

}
