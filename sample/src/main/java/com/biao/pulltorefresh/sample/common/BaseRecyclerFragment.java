package com.biao.pulltorefresh.sample.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biao.pulltorefresh.PtrLayout;
import com.biao.pulltorefresh.sample.R;
import com.biao.pulltorefresh.sample.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biaowu.
 */
public class BaseRecyclerFragment extends BaseFragment {

    private PtrLayout mPtrLayout;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    public static BaseRecyclerFragment newInstance(String title) {
        BaseRecyclerFragment fragment = new BaseRecyclerFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ptr_recycler_view, container, false);
        mPtrLayout = (PtrLayout) view;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(buildLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(buildAdapter(view.getContext()));
        return view;
    }

    protected RecyclerView.Adapter buildAdapter(Context context) {
        mAdapter = new RecyclerAdapter();
        return mAdapter;
    }

    protected RecyclerView.LayoutManager buildLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    protected void load() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("text" + i);
        }
        mAdapter.replaceAll(data);
    }

    protected void loadMore() {
        int count = mAdapter.getItemCount();
        List<String> data = new ArrayList<>();
        for (int i = count; i < count + 20; i++) {
            data.add("text" + i);
        }
        mAdapter.addAll(data);
    }

    protected void setHeaderView(View view) {
        mPtrLayout.setHeaderView(view);
    }

    protected void setFooterView(View view) {
        mPtrLayout.setFooterView(view);
    }

    public PtrLayout getPtrLayout() {
        return mPtrLayout;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}