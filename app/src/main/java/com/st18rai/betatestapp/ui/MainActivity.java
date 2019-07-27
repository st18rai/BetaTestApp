package com.st18rai.betatestapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.st18rai.betatestapp.R;
import com.st18rai.betatestapp.adapter.SignalsAdapter;
import com.st18rai.betatestapp.interfaces.Constants;
import com.st18rai.betatestapp.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String passKey;
    private String userLogin;

    // request data
    private String pairs = "EURUSD, GBPUSD, USDJPY, USDCHF, USDCAD, AUDUSD, NZDUSD";
    private String dateFrom = "1479860023";
    private String dateTo = "1480066860";
    private String tradingSystem = "3";

    private MainViewModel mainViewModel;
    private SignalsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        progressBar.setVisibility(View.VISIBLE);

        setRecycler();

        if (getIntent().getExtras() != null) {
            passKey = getIntent().getExtras().getString(Constants.PASSKEY);
            userLogin = getIntent().getExtras().getString(Constants.LOGIN);
//            Log.wtf("TAG", "passKey: " + passKey);
        }

        mainViewModel.loadSignals(userLogin, tradingSystem, pairs, dateFrom, dateTo, passKey);

        mainViewModel.getSignals().observe(this, itemData -> {
            progressBar.setVisibility(View.GONE);
            adapter.setData(itemData);
        });

    }

    private void setRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SignalsAdapter();
        ScaleInAnimationAdapter newsAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        newsAnimationAdapter.setFirstOnly(false);

        recyclerView.setAdapter(adapter);
    }
}
