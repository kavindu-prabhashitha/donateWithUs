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

import com.app.donatewithus.databinding.ActivityMainBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class UserDashboardActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        replaceFragment(new HomeDashboardCommonFragment());

        MaterialToolbar userDashboardTopAppBar = findViewById(R.id.user_dashboard_topAppBar);
        DrawerLayout userDashboardSideDrawerLayout = findViewById(R.id.user_dashboard_drawer_layout);
        NavigationView userDashboardSideNavBar = findViewById(R.id.user_dashboard_sideNavBar);

        userDashboardTopAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDashboardSideDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        userDashboardSideNavBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                userDashboardSideDrawerLayout.closeDrawer(GravityCompat.START);

                switch (id){
                    case (R.id.user_dashboard_sidebar_menu_home):
                        replaceFragment(new HomeDashboardCommonFragment());
                        Toast.makeText(UserDashboardActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case (R.id.user_dashboard_sidebar_menu_donations):
                        Toast.makeText(UserDashboardActivity.this, "Donations is Clicked", Toast.LENGTH_SHORT).show();
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
        fragmentTransaction.replace(R.id.user_dashboard_frameLayout,fragment);
        fragmentTransaction.commit();
    }
}