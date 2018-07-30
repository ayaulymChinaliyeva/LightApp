package kz.lightapp.lightapp.Volunteer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;

import kz.lightapp.lightapp.R;

public class VolunteerProfile extends AppCompatActivity {

    TextView name_tv, surname_tv,  age_tv, gender_tv, city_tv, phonenumber_tv, email_tv, lang_tv, prof_tv;
    ImageView imageView;
    Intent intent;
    String result;
    JSONParser parser;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile);

        intent = getIntent();

        name_tv = (TextView) findViewById(R.id.name_textView);
        surname_tv = (TextView) findViewById(R.id.surname_textView);
        age_tv = (TextView) findViewById(R.id.age_textView);
        gender_tv = (TextView) findViewById(R.id.gender_textView);
        phonenumber_tv = (TextView) findViewById(R.id.textView16);
        city_tv = (TextView) findViewById(R.id.phone_textView);
        email_tv = (TextView) findViewById(R.id.email_textView);
        lang_tv = (TextView) findViewById(R.id.lang_textView);
        prof_tv = (TextView) findViewById(R.id.prof_textView);

        imageView = (ImageView) findViewById(R.id.imageView_org);

        if(intent.hasExtra("result")){

            result = intent.getStringExtra("result");
            parser = new JSONParser();
            try{
                json = new JSONObject(result.toString());
            }
            catch (JSONException e){
            }

            Iterator<?> keys = json.keys();

            while( keys.hasNext() ) {
                String key = (String) keys.next();
                //key = key.replaceAll("\\s+","");
                try{
                    //  json.get(key).toString();
                    if(key.equals("email")){
                        email_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("gender")){
                        gender_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("city")){
                        city_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("phone")){
                        phonenumber_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("birthdate")){
                        age_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("language_skills")){
                        lang_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("professional_skills")){
                        prof_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("firstname")){
                        name_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("lastname")){
                        surname_tv.setText(json.get(key).toString());
                    }

                }
                catch (JSONException e){

                }

            }
        }


    }
}