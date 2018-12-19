package dp.schoolandroid.view.ui.fragment;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.FragmentBaseWithDataBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.view.ui.activity.HomeActivity;
import dp.schoolandroid.view.ui.adapter.NewsFeedRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.BaseFragmentWithDataViewModel;


public class BaseFragmentWithData extends Fragment {
    FragmentBaseWithDataBinding binding;
    ActivityOptions options;
    ImageView action_menu;
    ImageView chat_menu;
    ArrayList<SectionTimeModel> data;
    NewsFeedRecyclerViewAdapter classRecyclerViewAdapter;

    public static BaseFragmentWithData newInstance() {
        BaseFragmentWithData fragment = new BaseFragmentWithData();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_base_with_data,container,false);
        binding.actionMenuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.drawer.openDrawer(GravityCompat.START);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final BaseFragmentWithDataViewModel viewModel = ViewModelProviders.of(this).get(BaseFragmentWithDataViewModel.class);
        observeViewModel(viewModel);

    }

    private void observeViewModel(BaseFragmentWithDataViewModel viewModel) {
        viewModel.getData().observe(this, new Observer<FeedsResponse>() {
            @Override
            public void onChanged(@Nullable FeedsResponse feedsResponse) {
                Toast.makeText(getContext(), "data changed", Toast.LENGTH_SHORT).show();
                if (feedsResponse != null) {
                    Toast.makeText(getContext(), "Thursday" + feedsResponse.getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                    ArrayList<FeedModel> feedModels = feedsResponse.getData();
                    initializeRecyclerViewAdapter(feedModels);
               }
            }
        });
    }

    private void initializeRecyclerViewAdapter(ArrayList<FeedModel> feedModels) {
        classRecyclerViewAdapter = new NewsFeedRecyclerViewAdapter(feedModels);
        binding.baseClassRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false));
        binding.baseClassRecyclerView.setAdapter(classRecyclerViewAdapter);
    }
}
