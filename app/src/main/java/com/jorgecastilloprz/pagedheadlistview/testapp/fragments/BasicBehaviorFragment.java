package com.jorgecastilloprz.pagedheadlistview.testapp.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgecastilloprz.pagedheadlistview.PagedHeadListView;
import com.jorgecastilloprz.pagedheadlistview.testapp.R;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.FirstHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.SecondHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.utils.FragmentTypes;

/**
 * Created by jorge on 31/07/14.
 */
public class BasicBehaviorFragment extends Fragment {

    private View rootView;
    private PagedHeadListView mPagedHeadList;

    private Typeface robotoThin;
    private Typeface robotoLight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null)
            return rootView;

        int fragmentType = getArguments().getInt("type", FragmentTypes.BASIC.ordinal());

        switch (fragmentType) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_basic_behaviour, container, false);
                break;
        }

        robotoLight = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Light.ttf");
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Thin.ttf");

        mPagedHeadList = (PagedHeadListView) rootView.findViewById(R.id.pagedHeadListView);
        mPagedHeadList.addFragmentToHeader(new FirstHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new SecondHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new FirstHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new SecondHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new FirstHeaderFragment());

        return rootView;
    }
}
