package com.example.sipo.infograff;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.sipo.infograff.Bomber.BomberFrag;
import com.example.sipo.infograff.Event.EventFrag;
import com.example.sipo.infograff.Gallery.GalleryFrag;
import com.example.sipo.infograff.Spot.SpotFrag;
import com.example.sipo.infograff.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    ImageView btninfo;
    //String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.awesomeToolbar);
        ////toolbar.setSubtitle("");

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        //TABS
        mViewPager = (ViewPager)findViewById(R.id.v_tabs);

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        btninfo = (ImageView)findViewById(R.id.btninfo);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ActivityInfo.class));
            }
        });

        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        //mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.pinkMuda));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));

        mSlidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);

        /*ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);*/

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null && !networkInfo.isConnected()){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
            alertDialogBuilder
                    .setTitle("Could Not Connect")
                    .setMessage("Please check your connection and try again");

            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // Buatkan method hapus data kemudian dipanggil disini
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        //FirebaseMessaging.getInstance().subscribeToTopic("test");
        //FirebaseInstanceId.getInstance().getToken();

    }




}
