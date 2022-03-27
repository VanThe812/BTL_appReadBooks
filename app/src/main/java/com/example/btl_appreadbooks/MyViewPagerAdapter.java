package com.example.btl_appreadbooks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_appreadbooks.fragment.HomeFragment;
import com.example.btl_appreadbooks.fragment.MoiNhatFragment;
import com.example.btl_appreadbooks.fragment.PhoBienFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }




//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = "";
//        switch (position){
//            case 0: title = "Home"; break;
//            case 1: title = "Phổ biến"; break;
//            case 2: title = "Mới nhất"; break;
//        }
//        return title;
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new PhoBienFragment();
            case 2:
                return new MoiNhatFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
