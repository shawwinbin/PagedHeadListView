package com.jorgecastilloprz.pagedheadlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
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

    //Custom attrs
    private float headerHeight;
    private boolean interceptHeaderTouch;
    private int indicatorBgColor;
    private int indicatorColor;

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
            interceptHeaderTouch = a.getBoolean(R.styleable.PagedHeadListView_interceptHeaderTouch, false);
            indicatorBgColor = a.getColor(R.styleable.PagedHeadListView_indicatorBgColor, getResources().getColor(R.color.material_blue));
            indicatorColor = a.getColor(R.styleable.PagedHeadListView_indicatorColor, getResources().getColor(R.color.material_light_blue));

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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isContainedInHeader() && interceptHeaderTouch)
        {
            Log.d("PagedHeadListView", "TRUE!");
            return super.onTouchEvent(ev);
        }
        else {
            Log.d("PagedHeadListView", "FALSE.");
            return super.onTouchEvent(ev);
        }
    }

    private boolean isContainedInHeader() {

        Rect r = new Rect();
        headerView.getLocalVisibleRect(r);
        Log.d("PagedHeadListView", r.height() + "");

        return true;
    }
}
