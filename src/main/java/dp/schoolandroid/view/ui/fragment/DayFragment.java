package dp.schoolandroid.view.ui.fragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import dp.schoolandroid.service.model.global.TeacherSchedule;
import dp.schoolandroid.view.ui.adapter.DayRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.DayFragmentViewModel;


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
        DayFragmentViewModel viewModel = ViewModelProviders.of(this).get(DayFragmentViewModel.class);
        binding.setViewModel(viewModel);
        DayRecyclerViewAdapter dayRecyclerViewAdapter = new DayRecyclerViewAdapter(dayData);
        binding.dayRecyclerView.setAdapter(dayRecyclerViewAdapter);
        binding.dayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
