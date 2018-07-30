package kz.lightapp.lightapp.Organization;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.widget.Toast;
import android.text.format.DateFormat;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Iterator;

import kz.lightapp.lightapp.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;


public class AddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Intent intent;

    EditText eventName_et, eventReq_et, eventRewards_et, eventPeople_et, coordName_et, coordPhone_et, eventStart_date, eventEnd_date;
    Spinner city_spinner;
    String url = "http://madina.mthd.kz/technovation/add_event.php?";
    Button addProject_button;
    String eventName, eventReq, eventRewards, eventPeople, coordName, coordPhone, eventStart, eventEnd, city;
    String[] arraySpinner = new String[] {
            "Almaty", "Astana", "Atyrau", "Aqtau", "Aqtobe", "Kyzylorda", "Oskemen", "Oral", "Shymkent", "Taraz", "Taldykorgan", "Zhezkazgan", "Semey", "Pavlodar"};
    String full_url;
    String finalDateTime;
    String email;

    String result;
    JSONParser parser;
    JSONObject json;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;


    Button pickStartDate_button, pickEndDate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        intent = getIntent();

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
            key = key.replaceAll("\\s+", "");
            try {
                email = json.get("email").toString();
                break;
            } catch (JSONException e) {
            }

        }

        pickStartDate_button = (Button) findViewById(R.id.start_button);

        pickStartDate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEventActivity.this, AddEventActivity.this,
                        year, month, day);
                datePickerDialog.show();
            }
        });
        eventName_et = (EditText) findViewById(R.id.eventName_editText);
        eventReq_et = (EditText) findViewById(R.id.evenReqs_editText);
        eventRewards_et = (EditText) findViewById(R.id.eventRewsrds_editText);
        eventPeople_et = (EditText) findViewById(R.id.eventPeople_editText);
        coordName_et = (EditText) findViewById(R.id.coordName_editText);
        coordPhone_et = (EditText) findViewById(R.id.coordPhone_editText);


        city_spinner = (Spinner) findViewById(R.id.eventCity_spinner);
        addProject_button = (Button) findViewById(R.id.addProject_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(adapter);
        addProject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = eventName_et.getText().toString();
                eventReq = eventReq_et.getText().toString();
                eventRewards = eventRewards_et.getText().toString();
                eventPeople = eventPeople_et.getText().toString();
                coordName = coordName_et.getText().toString();
                coordPhone = coordPhone_et.getText().toString();

                city = city_spinner.getSelectedItem().toString();

                if(eventName.matches("") || eventReq.matches("") || eventRewards.matches("") ||
                        eventPeople.matches("") || coordName.matches("") || coordPhone.matches("") ||
                        city.matches("")){

                    Toast.makeText(AddEventActivity.this, "Please, fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    int eventPeopleInt = Integer.parseInt(eventPeople);
                    String new_url = "event_name="+eventName+"&coordinator_name="+coordName+"&coordinator_phone="+coordPhone;
                    String new_url2 = "&event_start="+finalDateTime+"&event_requirements="+eventReq+"&event_rewards="+eventRewards+"&org_mail="+email+"&event_people="+eventPeopleInt+"&event_city="+city;

                    full_url = url+new_url+new_url2;

                    full_url = full_url.replaceAll("\\s+","%20");

                    final RequestQueue req = Volley.newRequestQueue(AddEventActivity.this);
                    StringRequest sreq = new StringRequest(Request.Method.GET, full_url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if(response.replaceAll("\\s+","").equals("SUCCESS")){


                                        Toast.makeText(AddEventActivity.this, "Success!", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(AddEventActivity.this, VolProfileActivity.class);
//                                        intent.putExtra("result", response);
//
//                                        startActivity(intent);
//
                                    }
                                    else{
                                        Toast.makeText(AddEventActivity.this, full_url, Toast.LENGTH_LONG).show();
                                        Log.d("", full_url);
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
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month+1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddEventActivity.this, AddEventActivity.this,
                hour, minute, DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        finalDateTime = yearFinal+"-"+monthFinal+"-"+dayFinal+" "+hourFinal+":"+minuteFinal+":"+minuteFinal;

        Toast.makeText(AddEventActivity.this, finalDateTime, Toast.LENGTH_SHORT).show();
    }
}
