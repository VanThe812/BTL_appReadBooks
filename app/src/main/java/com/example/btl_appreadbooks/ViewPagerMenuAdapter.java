package com.example.btl_appreadbooks;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_appreadbooks.fragment.HomeFragment;
import com.example.btl_appreadbooks.fragment.MoiNhatFragment;
import com.example.btl_appreadbooks.fragment.PhoBienFragment;
import com.example.btl_appreadbooks.fragmentMenu.MenuTheLoaiFragment;
import com.example.btl_appreadbooks.fragmentMenu.MenuTrangChuFragment;
import com.example.btl_appreadbooks.fragmentMenu.MenuUserFragment;

public class ViewPagerMenuAdapter extends FragmentStatePagerAdapter {
    public ViewPagerMenuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MenuTrangChuFragment();
            case 1:
                return new MenuTheLoaiFragment();
            case 2:
                return new MenuUserFragment();
            default:
                return new MenuTrangChuFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
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
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//         switch (position){
//            case 0:
//                return new MenuTrangChuFragment();
//            case 1:
//                return new MenuTheLoaiFragment();
//            case 2:
//                return new MenuUserFragment();
//            default:
//                return new MenuTrangChuFragment();
//        }
//    }

//    @Override
//    public int getItemCount() {
//        return 3;
//    }
}
