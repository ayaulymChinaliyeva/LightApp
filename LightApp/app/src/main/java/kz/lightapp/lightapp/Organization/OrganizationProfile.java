package kz.lightapp.lightapp.Organization;
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

public class OrganizationProfile extends AppCompatActivity {

    TextView name_tv, email_tv, mission_tv, address_tv, leader_tv, phonenumber_tv, website_tv, category_tv;
    ImageView imageView;
    Intent intent;
    String result;
    JSONParser parser;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_profile);

        intent = getIntent();

        name_tv = (TextView) findViewById(R.id.name_textView);
        mission_tv = (TextView) findViewById(R.id.mission_textView);
        address_tv= (TextView) findViewById(R.id.address_textView);
        leader_tv = (TextView) findViewById(R.id.leader_textView);
        phonenumber_tv = (TextView) findViewById(R.id.phoneNumber_textView);
        website_tv= (TextView) findViewById(R.id.website_textView);
        email_tv = (TextView) findViewById(R.id.email_textView);
        imageView = (ImageView) findViewById(R.id.imageView_org);
        category_tv =(TextView) findViewById(R.id.category_textView);

        if(intent.hasExtra("result")){
            result = intent.getStringExtra("result");
            result = result.replaceAll("\t","");
            parser = new JSONParser();
            try{
                json = new JSONObject(result.toString());
            }
            catch (JSONException e){
            }

            Iterator<?> keys = json.keys();

            while( keys.hasNext() ) {
                String key = (String) keys.next();
                key = key.replaceAll("\\s+","");
                try{
                    //  json.get(key).toString();
                    if(key.equals("name")){
                        name_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("mission")){
                        mission_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("address")){
                        address_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("category")){
                        category_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("email")){
                        email_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("phone_number")){
                        phonenumber_tv.setText(json.get(key).toString());
                    }
                    else if(key.equals("website")){
                        website_tv.setText(json.get(key).toString());
                    }

                }
                catch (JSONException e){

                }

            }
        }


    }
}