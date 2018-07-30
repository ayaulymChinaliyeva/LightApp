package kz.lightapp.lightapp.Organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import kz.lightapp.lightapp.Organization.MainOrganizationActivity;
import kz.lightapp.lightapp.R;
import kz.lightapp.lightapp.Volunteer.AuthorizedVolunteerMainActivity;
import kz.lightapp.lightapp.Volunteer.VolunteersLoginActivity;

public class OrganizationLoginActivity extends AppCompatActivity {
    String result, email, password, emailFromIntent, passwordFromIntent;
    EditText email_editText, password_editText;
    Button signinButton;
    String url = "http://madina.mthd.kz/technovation/login_organization.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login);
        final Intent intent = getIntent();

        signinButton =(Button) findViewById(R.id.loginButton);
        email_editText =(EditText) findViewById(R.id.emailOrgLogin);
        password_editText =(EditText) findViewById(R.id.passwordOrgLogin);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://madina.mthd.kz/technovation/login_organization.php?";
                email = email_editText.getText().toString();
                password = password_editText.getText().toString();
                if(intent.hasExtra("result")){
                    result = intent.getStringExtra("result");
                    String[] splitted = result.split("\\s+");
                    emailFromIntent = splitted[0];
                    passwordFromIntent = splitted[1];

                    if(email.matches(emailFromIntent) && password.matches(passwordFromIntent)){
                        String new_url = "email="+email+"&password="+password;
                        url = url + new_url;
                        //Toast.makeText(VolLoginActivity.this, "Success", Toast.LENGTH_LONG).show();

                        final RequestQueue req = Volley.newRequestQueue(OrganizationLoginActivity.this);
                        StringRequest sreq = new StringRequest(Request.Method.GET, url,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        if(response.replaceAll("\\s+","").equals("FALSE")){
                                            Toast.makeText(OrganizationLoginActivity.this, "Email or password is not correct", Toast.LENGTH_SHORT).show();
                                        }
                                        else{

                                            Intent intent = new Intent(OrganizationLoginActivity.this, MainOrganizationActivity.class);
                                            intent.putExtra("result", response);
                                            startActivity(intent);
                                        }
                                        req.stop();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                error.printStackTrace();
                                req.stop();
                            }
                        });
                        req.add(sreq);
                    }
                }

                else{
                    url = "http://madina.mthd.kz/technovation/login_organization.php?";
                    String new_url = "email="+email+"&password="+password;
                    url = url + new_url;

                    final RequestQueue req = Volley.newRequestQueue(OrganizationLoginActivity.this);

                    StringRequest sreq = new StringRequest(Request.Method.GET, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    if(response.replaceAll("\\s+","").equals("FALSE")){
                                        Toast.makeText(OrganizationLoginActivity.this, "Email or password is not correct", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Intent intent = new Intent(OrganizationLoginActivity.this, MainOrganizationActivity.class);
                                        intent.putExtra("result", response);

                                        startActivity(intent);
                                    }
                                    req.stop();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            error.printStackTrace();
                            req.stop();
                        }
                    });
                    req.add(sreq);
                }
            }
        });
    }
}
