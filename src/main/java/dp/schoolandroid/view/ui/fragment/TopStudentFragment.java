package dp.schoolandroid.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.FragmentTopStudentBinding;
import dp.schoolandroid.view.ui.activity.TeacherHomeActivity;

public class TopStudentFragment extends Fragment {
    FragmentTopStudentBinding binding;
    private String memberType;

    @Inject
    public TopStudentFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_top_student,container,false);
        memberType = Objects.requireNonNull(getArguments()).getString(ConfigurationFile.Constants.MEMBER_Key);
        setupToolbar();
        return binding.getRoot();
    }

    private void setupToolbar() {
        binding.topStudentFragmentToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        binding.topStudentFragmentToolbar.setNavigationOnClickListener(v -> TeacherHomeActivity.drawer.openDrawer(GravityCompat.START));
    }
}
