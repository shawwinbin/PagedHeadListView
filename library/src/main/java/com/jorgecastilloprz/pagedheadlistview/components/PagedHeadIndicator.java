package com.jorgecastilloprz.pagedheadlistview.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.jorgecastilloprz.pagedheadlistview.R;

/**
 * Created by jorge on 5/08/14.
 */
public class PagedHeadIndicator extends AbstractPagedHeadIndicator {

    private View indicatorView;
    private Context context;

    public PagedHeadIndicator(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PagedHeadIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PagedHeadIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    @Override
    public void init() {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.indicator_height));
        setLayoutParams(layoutParams);

        setOrientation(HORIZONTAL);
        setWeightSum(100);
        setBackgroundColor(getResources().getColor(R.color.material_blue)); //default indicator bg color

        addIndicatorView();
    }

    private void addIndicatorView() {
        LinearLayout.LayoutParams indicatorParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);

        indicatorView = new View(context);
        indicatorView.setBackgroundColor(getResources().getColor(R.color.material_light_blue)); //default indicator color

        addView(indicatorView, indicatorParams);
    }

    @Override
    public void addPage() {
        pageCount++;
        recalculateIndicatorWidth();
    }

    /**
     * Recalculates indicator width each time a new page is added to the pager
     */
    private void recalculateIndicatorWidth() {
        float newWeight = 100 / pageCount;
        LinearLayout.LayoutParams indicatorParams = (LinearLayout.LayoutParams) indicatorView.getLayoutParams();
        indicatorParams.weight = newWeight;
        indicatorView.setLayoutParams(indicatorParams);
    }

    @Override
    public void setBgColor(int bgColor) {
        setBackgroundColor(bgColor);
    }

    @Override
    public void setColor(int indicatorColor) {
        indicatorView.setBackgroundColor(indicatorColor);
    }
}
