package vy.com.covid19.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle bundle = getIntent().getExtras();
        //Set-up bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,homeFragment).commit();

    }

    //Set-up bot nav listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_states:
                    selectedFragment = new StatesFragment();
                    break;
                case R.id.nav_fav:
                    selectedFragment = new FavoriteFragment();
            }

            //Display Frag
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,selectedFragment).commit();
            return true;
        }
    };

    private void GetAPIForHome(){

    }
}
