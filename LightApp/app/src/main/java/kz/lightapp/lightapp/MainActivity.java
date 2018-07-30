package kz.lightapp.lightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stephentuso.welcome.WelcomeHelper;

import kz.lightapp.lightapp.Organization.OrganizationSignUpActivity;
import kz.lightapp.lightapp.Volunteer.UnauthorizedVolunteerMainActivity;

public class MainActivity extends AppCompatActivity {
    WelcomeHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeScreen = new WelcomeHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    public void openVolunteerActivity(View view){
        Intent intent = new Intent(this, UnauthorizedVolunteerMainActivity.class);
        startActivity(intent);
    }
    public void openOrganizationActivity(View view){
        Intent intent = new Intent(this, OrganizationSignUpActivity.class);
        startActivity(intent);
    }

}
