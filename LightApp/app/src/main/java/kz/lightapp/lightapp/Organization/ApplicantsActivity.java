package kz.lightapp.lightapp.Organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

import kz.lightapp.lightapp.R;

public class ApplicantsActivity extends AppCompatActivity {
    TextView nameSurname_tv, email_tv, age_tv, volLang_tv, volProf_tv;
    Button accept_button, decline_button;
    Intent intent;
    String result, email;
    JSONParser parser;
    JSONObject json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants);

        nameSurname_tv = (TextView) findViewById(R.id.nameSurname_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);
        age_tv = (TextView) findViewById(R.id.age_tv);
        volLang_tv = (TextView) findViewById(R.id.lang_textView);
        volProf_tv = (TextView) findViewById(R.id.volProfSkills_textView5);

        accept_button = (Button) findViewById(R.id.accept_button);
        decline_button = (Button) findViewById(R.id.decline_button);

        intent = getIntent();

        result = intent.getStringExtra("result");
        result = result.replaceAll("\t","");

        nameSurname_tv.setText("Ayaulym Chinaliyeva");
        email_tv.setText("aya@aya.com");
        age_tv.setText("18");

        volLang_tv.setText("English, Russian, Kazakh, German");
        volProf_tv.setText("Programming, Presenting");


        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ApplicantsActivity.this, "Application accepted", Toast.LENGTH_SHORT).show();
            }
        });

        decline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ApplicantsActivity.this, "Application declined", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
