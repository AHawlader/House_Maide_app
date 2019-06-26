package com.example.arifh.house_maide_app.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arifh.house_maide_app.Activities.RatingFragment;
import com.example.arifh.house_maide_app.Activities.ReviewServentFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private  final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> fragmentListTitle=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                ReviewServentFragment tab1=new ReviewServentFragment();
                return tab1;
            case 1:
                RatingFragment tab2=new RatingFragment();
                return tab2;
                default:
                    return fragmentList.get(i);
        }

    }

    @Override
    public int getCount() {
        return fragmentListTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentListTitle.get(position);
    }
    public void AddFragment(Fragment fragment,String Title){
        fragmentList.add(fragment);
        fragmentListTitle.add(Title);
    }
}
