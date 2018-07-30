package kz.lightapp.lightapp.Volunteer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.lightapp.lightapp.R;

public class VolunteersSignUpActivity extends AppCompatActivity {

    Button requestButton;
    TextView tv;
    String url = "http://madina.mthd.kz/technovation/add_volunteer.php?";

    EditText nameSurname_editText, age_editText, phoneNumber_editText;
    EditText city_editText, langSkills_editText, profSkills_editText, email_editText, password_editText;
    RadioGroup radioGroup;
    RadioButton radioButton;

    String nameSurname, name, surname, city, langSkills, profSkills, email, password, phoneNum, gender, ageString;
    int age;

    Intent intent1 = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signup);
        Intent intent = getIntent();

        requestButton = (Button) findViewById(R.id.requestButton);
        nameSurname_editText = (EditText) findViewById(R.id.nameSurn_editText);
        age_editText = (EditText) findViewById(R.id.age_editText);
        phoneNumber_editText = (EditText) findViewById(R.id.phone_editText);
        city_editText = (EditText) findViewById(R.id.city_editText);
        langSkills_editText = (EditText) findViewById(R.id.langSkills_editText);
        profSkills_editText = (EditText) findViewById(R.id.profSkills_editText);
        email_editText = (EditText) findViewById(R.id.email_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //radioButton = (RadioButton) findViewById(R.id);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = city_editText.getText().toString();
                ageString = age_editText.getText().toString();
                phoneNum = phoneNumber_editText.getText().toString();
                langSkills = langSkills_editText.getText().toString();
                profSkills = profSkills_editText.getText().toString();
                email = email_editText.getText().toString();
                password = password_editText.getText().toString();
                nameSurname = nameSurname_editText.getText().toString();

                if (city.matches("") || ageString.matches("") || phoneNum.matches("") ||
                        langSkills.matches("") || profSkills.matches("") ||
                        nameSurname.matches("") || email.matches("") ||
                        password.matches("")) {
                    Toast.makeText(VolunteersSignUpActivity.this, "Please, fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    age = Integer.parseInt(ageString);
                    String[] splitted = nameSurname.split("\\s+");
                    name = splitted[0];
                    surname = splitted[1];

                    String new_url = "firstname=" + name + "&gender=" + gender + "&language_skills=" + langSkills + "&lastname=" + surname + "&professional_skills=" + profSkills;
                    new_url = new_url + "&city=" + city + "&email=" + email + "&phone=" + phoneNum + "&birthdate=" + age + "&password=" + password;

                    url = url + new_url;

                    final RequestQueue req = Volley.newRequestQueue(VolunteersSignUpActivity.this);


                    StringRequest sreq = new StringRequest(Request.Method.GET, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    intent1.putExtra("result", response);
                                    req.stop();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            nameSurname_editText.setText("Something went wrong...");
                            error.printStackTrace();
                            req.stop();
                        }
                    });
                    req.add(sreq);

                    String result = email + " "+password;

                    Intent intent = new Intent(VolunteersSignUpActivity.this, VolunteersLoginActivity.class);
                    intent.putExtra("result",result);
                    startActivity(intent);

                }
            }
        });

    }

    public void checkbutton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioId);
        gender = radioButton.getText().toString();


    }
}

