package com.example.sipo.infograff;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;

import com.example.sipo.infograff.Bomber.BomberFrag;
import com.example.sipo.infograff.Event.EventFrag;
import com.example.sipo.infograff.Gallery.GalleryFrag;
import com.example.sipo.infograff.Spot.SpotFrag;


/**
 * Created by SIPO on 9/19/2016.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"SPOT LEGAL","EVENT","GALLERY","BOMBER"};
    int[] icons = new int[]{
            R.mipmap.ic_navigation_black_24dp,
            R.mipmap.ic_event_black_24dp,
            R.mipmap.ic_image_black_24dp,
            R.mipmap.ic_people_black_24dp};
    private int heightIcon;



    public MyAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int)(24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragg = null;
        if(position == 0){
            fragg = new SpotFrag();
        }else if (position == 1){
            fragg = new EventFrag();
        }else if (position == 2){
            fragg = new GalleryFrag();
        }else if (position == 3){
            fragg = new BomberFrag();
        }

        Bundle b = new Bundle();
        b.putInt("position",position);
        fragg.setArguments(b);
        return fragg;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable d =mContext.getResources().getDrawable(icons[position]);
        d.setBounds(0,0,heightIcon,heightIcon);

        ImageSpan is = new ImageSpan(d);

        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);



        return (sp);
    }
}
