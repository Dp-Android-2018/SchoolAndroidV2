package dp.schoolandroid.view.ui.viewholder;

import android.support.v7.widget.RecyclerView;

import dp.schoolandroid.databinding.ItemScheduleBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.viewmodel.ItemScheduleViewModel;

public class DayViewHolder extends RecyclerView.ViewHolder {
    private ItemScheduleBinding binding;

    public DayViewHolder(ItemScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindDay(SectionTimeModel sectionTimeModel) {
        if (binding.getViewModel() == null) {
            binding.setViewModel(new ItemScheduleViewModel(sectionTimeModel));
        } else {
            binding.getViewModel().sectionTimeModel = sectionTimeModel;
        }
    }
}
