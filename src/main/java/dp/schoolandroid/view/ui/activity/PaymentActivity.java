package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityPaymentBinding;
import dp.schoolandroid.viewmodel.PaymentActivityViewModel;

public class PaymentActivity extends AppCompatActivity {

    PaymentActivityViewModel viewModel;
    ActivityPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_payment);
        viewModel=ViewModelProviders.of(this).get(PaymentActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}
