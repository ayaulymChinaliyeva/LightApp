package kz.lightapp.lightapp.Organization;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kz.lightapp.lightapp.Organization.Fragments.MyApplicants;
import kz.lightapp.lightapp.Organization.Fragments.MyProfile;
import kz.lightapp.lightapp.Organization.Fragments.MyProjects;
import kz.lightapp.lightapp.R;
import kz.lightapp.lightapp.Volunteer.Fragments.VolunteerProjectsFragment;
import static kz.lightapp.lightapp.R.id.bottom_navigation_organization;

public class MainOrganizationActivity extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_organization);
        Intent intent = getIntent();
        message = intent.getStringExtra("result");
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loadFragment(new VolunteerProjectsFragment());
        Button button = (Button) findViewById(R.id.registerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainOrganizationActivity.this, AddEventActivity.class);
                intent.putExtra("result", message);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(bottom_navigation_organization);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.myProjectsOrganization:
                        Intent intent2 = new Intent(MainOrganizationActivity.this, AddEventActivity.class);
                        intent2.putExtra("result", message);
                        startActivity(intent2);
//                        fragment = new MyProjects();
                        break;

                    case R.id.myApplicantsOrganization:

                        Intent intent3 = new Intent(MainOrganizationActivity.this, ApplicantsActivity.class);
                        intent3.putExtra("result", message);
                        startActivity(intent3);
//                        fragment = new MyApplicants();
                        break;

                    case R.id.myProfileOrganization:
                        Intent intent1 = new Intent(MainOrganizationActivity.this, OrganizationProfile.class);
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
                    .replace(R.id.fragment_container_organization, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
