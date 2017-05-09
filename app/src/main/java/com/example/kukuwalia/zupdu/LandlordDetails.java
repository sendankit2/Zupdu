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

public class LandlordDetails extends AppCompatActivity {
    private static final String TAG = "LandLordDetails";
    private SectionsPagerAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_details);

            mSectionsPageAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

            tabLayout.getTabAt(0).setText("Landlord");
            tabLayout.getTabAt(1).setText("Roommate");

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
                            Intent intent1 = new Intent(LandlordDetails.this, ActivityOne.class);
                            startActivity(intent1);
                            break;

                        case R.id.filter:
                            Intent intent2 = new Intent(LandlordDetails.this, ActivityTwo.class);
                            startActivity(intent2);
                            break;

                        case R.id.logout:
                            Intent intent3 = new Intent(LandlordDetails.this, ActivityThree.class);
                            startActivity(intent3);
                            break;
                    }


                    return false;
                }
            });

        }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab5Fragment());
        adapter.addFragment(new Tab6Fragment());
        viewPager.setAdapter(adapter);
    }
}