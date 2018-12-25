package dp.schoolandroid.view.ui.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.FragmentDayBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.view.ui.adapter.DayRecyclerViewAdapter;
/*
* this is the fragment to be shown into viewpager
*
* */

public class DayFragment extends Fragment {
    FragmentDayBinding binding;
    private ArrayList<SectionTimeModel> dayData = new ArrayList<>();

    public DayFragment() {
    }

    @SuppressLint("ValidFragment")
    public DayFragment(ArrayList<SectionTimeModel> dayData) {
        this.dayData = dayData;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false);
        setupDayRecyclerView();
        return binding.getRoot();
    }

    private void setupDayRecyclerView() {
        DayRecyclerViewAdapter dayRecyclerViewAdapter = new DayRecyclerViewAdapter(dayData);
        binding.dayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        binding.dayRecyclerView.setAdapter(dayRecyclerViewAdapter);
    }
}
