package kz.lightapp.lightapp.Volunteer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import kz.lightapp.lightapp.R;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerProjectsFragment;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerRankingsFragment;
import static kz.lightapp.lightapp.R.id.bottom_navigation_authVol;

public class AuthorizedVolunteerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_volunteer_main);
        Intent intent = getIntent();
        final String message = intent.getStringExtra("result");

        loadFragment(new VolunteerProjectsFragment());

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(bottom_navigation_authVol);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.volunteerProjectItem:
                        Intent intent2 = new Intent(AuthorizedVolunteerMainActivity.this, GetEventsActivity.class);
                        intent2.putExtra("result", message);
                        startActivity(intent2);
//                        fragment = new VolunteerProjectsFragment();
                        break;

                    case R.id.volunteerRankingsItem:
                        fragment = new VolunteerRankingsFragment();
                        break;

                    case R.id.volunteerProfileItem:
                        Intent intent1 = new Intent(AuthorizedVolunteerMainActivity.this, VolunteerProfile.class);
                        intent1.putExtra("result", message);
                        startActivity(intent1);
                        break;
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_authVol, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
