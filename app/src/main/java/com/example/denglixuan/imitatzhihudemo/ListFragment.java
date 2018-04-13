package com.example.denglixuan.imitatzhihudemo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by denglixuan on 2018/1/8.
 */

public class ListFragment extends Fragment {
    public static String TAG = "TAG";
    private RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    List<String> listDatas = new ArrayList<>();
    TextAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < 5; i++) {
            listDatas.add(i + "");
        }
        final TextAdapter adapter = new TextAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        mSmartRefreshLayout.setEnableRefresh(false);
        //mSmartRefreshLayout.setEnableAutoLoadmore(true);
        mSmartRefreshLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("", "");
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
              /*  List<String> listDatass = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    listDatas.add(i + "");
                }
                listDatas.addAll(listDatass);
                adapter.notifyDataSetChanged();

                //mSmartRefreshLayout.setLoadmoreFinished(true);
                if (listDatas.size() >= 15) {
                    mSmartRefreshLayout.setLoadmoreFinished(true);
                    //mSmartRefreshLayout.setEnableLoadmore(true);

                }*/
            }
        });
        mSmartRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterPulling---------------->");
                scrollView.setNeedScroll(true);
            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterReleased---------------->");
                //scrollView.setNeedScroll(true);
            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterReleasing---------------->");
                //scrollView.setNeedScroll(true);
            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {
                //Log.i(TAG, "onFooterStartAnimator---------------->");
            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {
                Log.i(TAG, "onFooterFinish---------------->");
                //scrollView.setNeedScroll(true);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.i(TAG, "onLoadmore---------------->");
                //scrollView.setNeedScroll(true);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }

            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

            }
        });
    }

    JudgeNestedScrollView scrollView;

    public void setScrollView(JudgeNestedScrollView scrollView) {
        this.scrollView = scrollView;
    }

    public class TextAdapter extends RecyclerView.Adapter<TextViewHolder> {


        @Override
        public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate
                    (R.layout.item_recyclerview, parent, false);
            return new TextViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TextViewHolder holder, int position) {
            holder.mTextView.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return listDatas.size();
        }
    }

    ;

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_recycler_view);
        }
    }

}
