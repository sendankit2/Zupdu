package com.example.kukuwalia.zupdu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by Kuku Walia on 4/24/2017.
 */

public class MainUserArea extends AppCompatActivity {
    private static final String TAG = "MainUserArea";

    private SectionsPagerAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_area);

        mSectionsPageAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.getTabAt(0).setText("List Your Space");
        tabLayout.getTabAt(1).setText("Room");
        tabLayout.getTabAt(2).setText("House/Apt");
        tabLayout.getTabAt(3).setText("Roommate");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        break;

                    case R.id.profile:
                        Intent intent1 = new Intent(MainUserArea.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.filter:
                        Intent intent2 = new Intent(MainUserArea.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;

                    case R.id.logout:
                        Intent intent3 = new Intent(MainUserArea.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        adapter.addFragment(new Tab3Fragment());
        adapter.addFragment(new Tab4Fragment());
        viewPager.setAdapter(adapter);
    }
}