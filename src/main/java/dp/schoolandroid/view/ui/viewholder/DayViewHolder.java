package dp.schoolandroid.view.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import dp.schoolandroid.databinding.ItemScheduleBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;

public class DayViewHolder extends RecyclerView.ViewHolder {
    private ItemScheduleBinding binding;

    public DayViewHolder(ItemScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindDay(SectionTimeModel sectionTimeModel) {
        binding.tvScheduleClassName.setText(sectionTimeModel.getClassName());
        binding.tvScheduleClassGrade.setText(sectionTimeModel.getGrade());
        binding.tvScheduleClassNumberOfStudents.setText(sectionTimeModel.getStudentsCount());
        binding.tvItemToFrom.setText(sectionTimeModel.getTo());
        binding.tvItemScheduleFrom.setText(sectionTimeModel.getFrom());
    }
}
