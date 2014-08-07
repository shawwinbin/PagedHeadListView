package com.jorgecastilloprz.pagedheadlistview.testapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgecastilloprz.pagedheadlistview.PagedHeadListView;
import com.jorgecastilloprz.pagedheadlistview.testapp.R;
import com.jorgecastilloprz.pagedheadlistview.testapp.adapters.MockListAdapter;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.FifthHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.FirstHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.FourthHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.SecondHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.fragments.headerfragments.ThirdHeaderFragment;
import com.jorgecastilloprz.pagedheadlistview.testapp.utils.FragmentTypes;

import java.util.ArrayList;

/**
 * Created by jorge on 31/07/14.
 */
public class BasicBehaviorFragment extends Fragment {

    private View rootView;
    private PagedHeadListView mPagedHeadList;

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

        initPagedHeadList();

        return rootView;
    }

    /**
     * Initializes list with mock fragments for the header and plenty of mock items
     */
    private void initPagedHeadList() {
        mPagedHeadList = (PagedHeadListView) rootView.findViewById(R.id.pagedHeadListView);

        mPagedHeadList.addFragmentToHeader(new FirstHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new SecondHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new ThirdHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new FourthHeaderFragment());
        mPagedHeadList.addFragmentToHeader(new FifthHeaderFragment());

        mPagedHeadList.setHeaderOffScreenPageLimit(4);

        ArrayList<String> mockItemList = new ArrayList<String>();

        for (int i = 0; i < 50; i++)
            mockItemList.add(getResources().getString(R.string.mock_item) + " " + (i+1));

        MockListAdapter mockListAdapter = new MockListAdapter(getActivity(), R.layout.mock_list_item, mockItemList);
        mPagedHeadList.setAdapter(mockListAdapter);
    }
}
