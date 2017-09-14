package com.example.mheljohn.qrsys;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenFragment extends Fragment {
    private FragmentTabHost mTabHost;

    public MenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tab, container, false);
        mTabHost = (FragmentTabHost)root.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Top"),
                LoginFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Bottom"),
                HomeFragment.class, null);
       // mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Category 3"),
       //         SignUpFragment.class, null);
        //mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("Category 4"),
         //       HomeFragment.class, null);
        //mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Tab3"),
        //        HomeFragment.class, null);
        return root;
    }

}
