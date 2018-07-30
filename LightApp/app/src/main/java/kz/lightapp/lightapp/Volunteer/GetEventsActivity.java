package kz.lightapp.lightapp.Volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

import kz.lightapp.lightapp.R;

public class GetEventsActivity extends AppCompatActivity {
    TextView eventName_tv, eventPeople_tv, eventCity_tv, eventDate_tv, eventReqs_tv, eventRewards_tv, coordName_tv, coordPhone_tv;
    String eventName, eventPeople, eventCity, eventDate, eventReqs, eventRewards, coordName, coordPhone;
    String url = "http://madina.mthd.kz/technovation/get_allEvents.php";
    Button button;
    Intent intent;
    String result;
    JSONParser parser;
    JSONObject json;
    int event_id;
    String org_mail, volunteer_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_events);

        eventName_tv = (TextView) findViewById(R.id.eventName_tv);
        eventCity_tv = (TextView) findViewById(R.id.city_tv);
        eventDate_tv = (TextView) findViewById(R.id.date_tv);
        eventPeople_tv = (TextView) findViewById(R.id.eventPeople_tv);
        eventReqs_tv = (TextView) findViewById(R.id.eventReqs_tv);
        eventRewards_tv = (TextView) findViewById(R.id.eventRewards_tv);
        coordName_tv = (TextView) findViewById(R.id.coordName_tv);
        coordPhone_tv = (TextView) findViewById(R.id.coordPhone_tv);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                if(intent.getStringExtra("result").equals("unauthorised")){
                    Intent intent2 = new Intent(GetEventsActivity.this, VolunteersSignUpActivity.class);
                    startActivity(intent2);
                }

                else{
                    String link = "http://madina.mthd.kz/technovation/add_apply.php?";
                    String message = intent.getStringExtra("result");
//                    Toast.makeText(GetEventsActivity.this, message, Toast.LENGTH_LONG).show();

                    try{
                        json = new JSONObject(message.toString());
                    }
                    catch (JSONException e){
                    }

                    Iterator<?> keys = json.keys();

                    while( keys.hasNext() ) {
                        String key = (String) keys.next();
                        key = key.replaceAll("\\s+","");
                        try{
                            //  json.get(key).toString();
                            if(key.equals("email")){
                                volunteer_mail = json.get(key).toString();
                                break;
                            }
                        }
                        catch (JSONException e){

                        }

                    }
                        String new_url = "event_id="+event_id+"&volunteer_mail="+volunteer_mail+"&org_mail="+org_mail;
                        String fffull_url = link+new_url;

                    final RequestQueue req = Volley.newRequestQueue(GetEventsActivity.this);

                    StringRequest sreq = new StringRequest(Request.Method.GET, fffull_url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(GetEventsActivity.this, "You applied successfully!", Toast.LENGTH_SHORT).show();
                                    req.stop();}


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

        final RequestQueue req = Volley.newRequestQueue(GetEventsActivity.this);

        StringRequest sreq = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.matches("FALSE")){
                            Toast.makeText(GetEventsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Toast.makeText(GetEventsActivity.this, "Success", Toast.LENGTH_SHORT).show();


                            response = response.replaceAll("\t","");
                            try{
                                json = new JSONObject(response.toString());
                            }
                            catch (JSONException e){
                            }

                            Iterator<?> keys = json.keys();
                            int i = 0;
                            while( keys.hasNext() ) {
                                String key = (String) keys.next();
//                                key = key.replaceAll("\\s+","");
                                try{
//                                    if(i==1){break;}
                                    event_id = Integer.parseInt(json.get("event_id").toString());
                                    org_mail = json.get("org_mail").toString();
//                                    i++;
                                    if(key.equals("event_name")){
                                        eventName_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("coordinator_name")){
                                        coordName_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("event_start")){
                                        eventDate_tv.setText(json.get(key).toString()+",");
                                    }
                                    else if(key.equals("event_requirements")){
                                        eventReqs_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("event_rewards")){
                                        eventRewards_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("coordinator_phone")){
                                        coordPhone_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("event_city")){
                                        eventCity_tv.setText(json.get(key).toString());
                                    }
                                    else if(key.equals("event_people")){
                                        eventPeople_tv.setText(json.get(key).toString()+" people needed");
                                    }


                                }
                                catch (JSONException e){

                                }

                            }




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
