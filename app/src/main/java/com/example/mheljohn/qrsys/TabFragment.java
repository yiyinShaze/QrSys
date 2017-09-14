package com.example.mheljohn.qrsys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabFragment extends Fragment {
    private FragmentTabHost mTabHost;



    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tab, container, false);
        mTabHost = (FragmentTabHost)root.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Login"),
                LoginFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Sign Up"),
                SignUpFragment.class, null);
        //mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Tab3"),
        //        HomeFragment.class, null);
        return root;
    }

}
