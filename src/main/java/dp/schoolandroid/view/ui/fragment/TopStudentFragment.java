package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.FragmentTopStudentBinding;
import dp.schoolandroid.viewmodel.FragmentTopStudentViewModel;
import dp.schoolandroid.viewmodel.MyCustomBarViewModel;

public class TopStudentFragment extends Fragment {

    FragmentTopStudentBinding binding;
    FragmentTopStudentViewModel viewModel;

    public static TopStudentFragment newInstance() {
        TopStudentFragment fragment = new TopStudentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_top_student,container,false);
        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        viewModel=ViewModelProviders.of(this).get(FragmentTopStudentViewModel.class);
        binding.setViewModel(viewModel);
        binding.topStudentActionBar.setViewModel(new MyCustomBarViewModel(getContext()));
        binding.topStudentActionBar.chatMenuImage.setVisibility(View.GONE);
        binding.topStudentActionBar.tvActionBarTitle.setText("Top Student");
    }


}
