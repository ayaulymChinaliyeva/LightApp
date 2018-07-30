package kz.lightapp.lightapp.Volunteer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import kz.lightapp.lightapp.R;

public class VolunteersLoginActivity extends AppCompatActivity {
    String result, email, password, emailFromIntent, passwordFromIntent;
    EditText email_editText, password_editText;
    Button signinButton;
    Intent intent;
    String url = "http://madina.mthd.kz/technovation/login_volunteer.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers_login);

        email_editText = (EditText) findViewById(R.id.emailSignIn_editText);
        password_editText = (EditText) findViewById(R.id.passwordSignin_editText);
        signinButton = (Button) findViewById(R.id.signin_button);

        intent = getIntent();



        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_editText.getText().toString();
                password = password_editText.getText().toString();
                if(intent.hasExtra("result")){
                    url = "http://madina.mthd.kz/technovation/login_volunteer.php?";

                    result = intent.getStringExtra("result");
                    String[] splitted = result.split("\\s+");
                    emailFromIntent = splitted[0];
                    passwordFromIntent = splitted[1];

                    if(email.matches(emailFromIntent) && password.matches(passwordFromIntent)){
                        String new_url = "email="+email+"&password="+password;
                        url = url + new_url;
                        //Toast.makeText(VolLoginActivity.this, "Success", Toast.LENGTH_LONG).show();

                        final RequestQueue req = Volley.newRequestQueue(VolunteersLoginActivity.this);
                        StringRequest sreq = new StringRequest(Request.Method.GET, url,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        if(response.replaceAll("\\s+","").equals("FALSE")){
                                            Toast.makeText(VolunteersLoginActivity.this, "Email or password is not correct", Toast.LENGTH_SHORT).show();
                                        }
                                        else{

                                            Intent intent = new Intent(VolunteersLoginActivity.this, AuthorizedVolunteerMainActivity.class);
                                            intent.putExtra("result", response);

                                            startActivity(intent);
//
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
                    url = "http://madina.mthd.kz/technovation/login_volunteer.php?";
                    String new_url = "email="+email+"&password="+password;
                    url = url + new_url;

                    final RequestQueue req = Volley.newRequestQueue(VolunteersLoginActivity.this);

                    StringRequest sreq = new StringRequest(Request.Method.GET, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    if(response.replaceAll("\\s+","").equals("FALSE")){
                                        Toast.makeText(VolunteersLoginActivity.this, "Email or password is not correct", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Intent intent = new Intent(VolunteersLoginActivity.this, AuthorizedVolunteerMainActivity.class);
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
