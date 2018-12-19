package dp.schoolandroid.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ItemScheduleBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.TeacherSchedule;
import dp.schoolandroid.view.ui.viewholder.DayViewHolder;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayViewHolder> {

    private ArrayList<SectionTimeModel> dayData;

    public DayRecyclerViewAdapter(ArrayList<SectionTimeModel> dayData) {
        this.dayData = dayData;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule, parent, false);
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.bindDay(dayData.get(position));
    }

    @Override
    public int getItemCount() {
        if (dayData !=null){
            return dayData.size();
        }
        return 0;
    }
}
