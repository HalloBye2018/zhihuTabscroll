package com.example.denglixuan.imitatzhihudemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_zhihu)
    Toolbar mToolbar;
    @BindView(R.id.viewPager_zhihu)
    FragmentViewPager mViewpager;
    /*@BindView(R.id.tab_layout)
    TabLayout mTabLayout;*/
    @BindView(R.id.tab_layout_copy)
    TabLayout mTabLayoutCopy;
    @BindView(R.id.nestedScrollView)
    JudgeNestedScrollView mScrollView;
    /* @BindView(R.id.viewPager_layout)
     RelativeLayout mRelativeLayout;*/
/*    @BindView(R.id.divider3)
    View divider3;*/
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

/*
    @BindView(R.id.scroll_View)
    ScrollView scroll_View;
*/

    public static String TAG = "TAG";
    private String[] titles = {"动态", "回答", "文章", "想法"};
    private List<Fragment> fragmentList;
    private int width, height;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.TranslucentStatusBar(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        mSmartRefreshLayout.setEnableLoadmore(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private void initViews() {

        //设置头布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置脚布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle("xhz");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mTabLayoutCopy.setVisibility(View.GONE);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //mTabLayout.setupWithViewPager(mViewpager);
        mTabLayoutCopy.setupWithViewPager(mViewpager);

        fragmentList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            ListFragment fragment = new ListFragment();
            fragment.setScrollView(mScrollView);
            fragmentList.add(fragment);

        }
        ZhiHuFragmentAdapter adapter = new ZhiHuFragmentAdapter(
                getSupportFragmentManager(), fragmentList, titles);


        mViewpager.setAdapter(adapter);

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

               /* int[] location = new int[2];
                divider3.getLocationInWindow(location);
                divider3.getLocationOnScreen(location);

                if (location[1] < Utils.dp2px(MainActivity.this, 1)) {
                    mTabLayoutCopy.setVisibility(View.VISIBLE);
                    mTabLayout.setVisibility(View.INVISIBLE);
                } else {
                    mTabLayoutCopy.setVisibility(View.GONE);
                    mTabLayout.setVisibility(View.VISIBLE);
                }*/
                /*int screenWidth = getScreenMetrics(MainActivity.this).x;
                int screenHeight = getScreenMetrics(MainActivity.this).y;
                Rect rect = new Rect(0, 0, screenWidth, screenHeight);
                int[] location = new int[2];
                view.getLocationInWindow(location);
                if (view.getLocalVisibleRect(rect)) {
                    mTabLayoutCopy.setVisibility(View.VISIBLE);
                    mTabLayout.setVisibility(View.INVISIBLE);
                } else {
                    //view已不在屏幕可见区域;
                    mTabLayoutCopy.setVisibility(View.GONE);
                    mTabLayout.setVisibility(View.VISIBLE);
                }*/
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                    //mSmartRefreshLayout.setEnabled(true);
                    //scroll_View.setEnabled(true);
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
                    //mSmartRefreshLayout.setEnabled(false);
                 /*   mScrollView.getParent().requestDisallowInterceptTouchEvent(true);
                    mScrollView.setEnabled(false);*/
                    mScrollView.setNeedScroll(false);
                    //scroll_View.setEnabled(false);
                }
            }
        });
       /* mSmartRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
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
            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterReleased---------------->");
            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterReleasing---------------->");
            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {
                Log.i(TAG, "onFooterStartAnimator---------------->");
            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {
                Log.i(TAG, "onFooterFinish---------------->");
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.i(TAG, "onLoadmore---------------->");
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }

            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

            }
        });*/
        mSmartRefreshLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i(TAG, "mSmartRefreshLayout---------------->");
            }
        });

    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //mViewpager.setFocusable(false);
       /* mRelativeLayout.setFocusable(true);
        mRelativeLayout.setFocusableInTouchMode(true);
        mRelativeLayout.requestFocus();*/

    }
}
