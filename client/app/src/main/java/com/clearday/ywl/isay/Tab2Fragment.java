package com.clearday.ywl.isay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearday.ywl.isay.adapter.Tab2Adapter;

public class Tab2Fragment extends Fragment {
    private static final int DATASET_COUNT = 60;
    protected RecyclerView mRecyclerView;
    protected Tab2Adapter mAdapter;
    protected LinearLayoutManager mLayoutManager;
    protected String[] mDataset;
    protected SwipeRefreshLayout refresher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.tab2recyclerView);
        setupRecyclerView();
        refresher = (SwipeRefreshLayout)rootView.findViewById(R.id.tab2refresher);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresher.setRefreshing(true);
                //refresher.setRefreshing(false);
            }
        });
        //FloatingActionButton fab=(FloatingActionButton)rootView.findViewById(R.id.fab);
        return  rootView;
    }

    private void setupRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Tab2Adapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

}
