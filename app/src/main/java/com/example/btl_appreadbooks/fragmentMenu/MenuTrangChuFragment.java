package com.example.btl_appreadbooks.fragmentMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_appreadbooks.MyViewPagerAdapter;
import com.example.btl_appreadbooks.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MenuTrangChuFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment_trangchu, container, false);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);

        myViewPagerAdapter = new MyViewPagerAdapter(getActivity());
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.setUserInputEnabled(false);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0: tab.setText("Home");

                    break;
                case 1: tab.setText("Phổ biến");break;
                case 2: tab.setText("Mới nhất");break;
            }
        }).attach();
        return view;
    }
}
