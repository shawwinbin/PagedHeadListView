package com.jorgecastilloprz.pagedheadlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jorgecastilloprz.pagedheadlistview.adapters.ViewPagerAdapter;

/**
 * Created by jorge on 2/08/14.
 */
public class PagedHeadListView extends ListView {

    private View headerView;
    private ViewPager mPager;
    private ViewPagerAdapter headerViewPagerAdapter;
    private float headerHeight;

    public PagedHeadListView(Context context) {
        super(context);
        init(null);
    }

    public PagedHeadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PagedHeadListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PagedHeadListView);
            headerHeight = a.getDimensionPixelSize(R.styleable.PagedHeadListView_headerHeight,
                    getContext().getResources().getDimensionPixelSize(R.dimen.default_header_height));

            a.recycle();
        }

        initializePagedHeader();
    }

    private void initializePagedHeader() {

        headerView = View.inflate(getContext(), R.layout.paged_header, null);

        AbsListView.LayoutParams headerViewParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) headerHeight);
        headerView.setLayoutParams(headerViewParams);

        mPager = (ViewPager) headerView.findViewById(R.id.headerViewPager);

        FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        headerViewPagerAdapter = new ViewPagerAdapter(fragmentManager);

        addHeaderView(headerView);
        mPager.setAdapter(headerViewPagerAdapter);
    }

    public void addFragmentToHeader(Fragment fragmentToAdd) {
        headerViewPagerAdapter.addFragment(fragmentToAdd);
    }

    /**
     * Tracks touch event to be able to intercept it if user is touching the header
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isContainedInHeader(ev))
            return true;
        else
            return false;
    }

    private boolean isContainedInHeader(MotionEvent ev) {

        return true;
    }
}
