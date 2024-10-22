package com.example.bottomnavigationexample;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Declare the BottomNavigationView and Fragment map
    private BottomNavigationView bottomNavigationView;
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the activity's layout

        // Initialize the BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Initialize the fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nav_home, new HomeFragment());
        fragmentMap.put(R.id.nav_profile, new ProfileFragment());
        fragmentMap.put(R.id.nav_notification, new NotificationFragment());
        fragmentMap.put(R.id.nav_settings, new SettingsFragment());

        // Set default fragment to HomeFragment on app start
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();

        // Set up the item selected listener for the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Retrieve the selected fragment from the map based on the clicked item ID
                Fragment selectedFragment = fragmentMap.get(item.getItemId());

                // If a valid fragment was found, replace the current fragment
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, selectedFragment)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}
