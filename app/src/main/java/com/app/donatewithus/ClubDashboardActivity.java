package com.app.donatewithus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class ClubDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_dashboard);
        replaceFragment(new HomeDashboardCommonFragment());

        MaterialToolbar clubDashboardTopAppBar = findViewById(R.id.club_dashboard_topAppBar);
        DrawerLayout clubDashboardSideDrawerLayout = findViewById(R.id.club_dashboard_drawer_layout);
        NavigationView clubDashboardSideNavBar = findViewById(R.id.club_dashboard_sideNavBar);

        clubDashboardTopAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubDashboardSideDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        clubDashboardSideNavBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                clubDashboardSideDrawerLayout.closeDrawer(GravityCompat.START);

                switch (id){
                    case (R.id.club_dashboard_sidebar_menu_home):
                        replaceFragment(new HomeDashboardCommonFragment());
                        Toast.makeText(ClubDashboardActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case (R.id.club_dashboard_sidebar_menu_events):
                        Toast.makeText(ClubDashboardActivity.this, "Events is Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.club_dashboard_frameLayout,fragment);
        fragmentTransaction.commit();
    }
}