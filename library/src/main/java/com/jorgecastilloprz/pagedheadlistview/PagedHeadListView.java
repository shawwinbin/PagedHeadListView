package com.jorgecastilloprz.pagedheadlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.jorgecastilloprz.pagedheadlistview.adapters.ViewPagerAdapter;

/**
 * Created by jorge on 2/08/14.
 */
public class PagedHeadListView extends ListView {

    private View headerView;
    private LayoutInflater inflater;
    private ViewPager mPager;
    private ViewPagerAdapter headerViewPagerAdapter;

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

        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PagedHeadListView);
//            completionPercent = a.getFloat(R.styleable.ExpandablePanelView_completionPercent, 0.75f);
//            invertBehavior = a.getBoolean(R.styleable.ExpandablePanelView_invertBehavior, false);
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Just first time
        if (isFirstMeasure())
            initializePagedHeader();

    }

    private boolean isFirstMeasure() {
        return headerView == null;
    }

    private void initializePagedHeader() {
        headerView = inflater.inflate(R.layout.paged_header, null);

        mPager = (ViewPager) headerView.findViewById(R.id.headerViewPager);

        FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        headerViewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        mPager.setAdapter(headerViewPagerAdapter);

        addHeaderView(headerView);
    }

    public void addFragmentToHeader(Fragment fragmentToAdd) {
        headerViewPagerAdapter.addFragment(fragmentToAdd);
    }
}
