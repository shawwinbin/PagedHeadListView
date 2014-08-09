package com.jorgecastilloprz.pagedheadlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
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
import com.jorgecastilloprz.pagedheadlistview.components.PagedHeadIndicator;
import com.jorgecastilloprz.pagedheadlistview.utils.IndicatorTypes;

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
    private PagedHeadIndicator indicator;
    private int indicatorType;

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
            indicatorType = a.getInteger(R.styleable.PagedHeadListView_indicatorType, IndicatorTypes.BOTTOMALIGNED.ordinal());

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

        indicator = new PagedHeadIndicator(getContext());
        indicator.setBgColor(indicatorBgColor);
        indicator.setColor(indicatorColor);

        switch (indicatorType) {
            case 0:
                addHeaderView(headerView);
                break;
            case 1:
                addHeaderView(indicator);
                addHeaderView(headerView);
                break;
            case 2:
                addHeaderView(headerView);
                addHeaderView(indicator);
                break;
        }

        mPager.setAdapter(headerViewPagerAdapter);
        mPager.setOnPageChangeListener(indicator);
    }

    public void addFragmentToHeader(Fragment fragmentToAdd) {
        indicator.addPage();
        headerViewPagerAdapter.addFragment(fragmentToAdd);
    }

    public void setHeaderOffScreenPageLimit(int offScreenPageLimit){
        mPager.setOffscreenPageLimit(offScreenPageLimit);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isContainedInHeader() && interceptHeaderTouch)
        {
            return super.onTouchEvent(ev);
        }
        else {
            return super.onTouchEvent(ev);
        }
    }

    private boolean isContainedInHeader() {

        Rect r = new Rect();
        headerView.getLocalVisibleRect(r);
        return true;
    }
}
