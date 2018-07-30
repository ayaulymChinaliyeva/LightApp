package kz.lightapp.lightapp.Volunteer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kz.lightapp.lightapp.Organization.AddEventActivity;
import kz.lightapp.lightapp.Organization.MainOrganizationActivity;
import kz.lightapp.lightapp.R;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerProfileFragment;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerProjectsFragment;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerRankingsFragment;

import static kz.lightapp.lightapp.R.id.bottom_navigation;


public class UnauthorizedVolunteerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauthorized_volunteer_main);

        Intent intent = getIntent();

        loadFragment(new VolunteerProjectsFragment());

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.projects:
//                        fragment = new VolunteerProjectsFragment();

                        Intent intent2 = new Intent(UnauthorizedVolunteerMainActivity.this, GetEventsActivity.class);
                        intent2.putExtra("result", "unauthorised");
                        startActivity(intent2);
                        break;

                    case R.id.rankings:
                        fragment = new VolunteerRankingsFragment();
                        break;

                    case R.id.profile:
                        fragment = new VolunteerProfileFragment();
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
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
