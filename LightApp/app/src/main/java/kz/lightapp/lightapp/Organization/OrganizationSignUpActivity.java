package kz.lightapp.lightapp.Organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import kz.lightapp.lightapp.R;

public class OrganizationSignUpActivity extends AppCompatActivity {
    EditText orgNameInput;
    EditText orgMissionInput;
    EditText orgAddress;
    EditText phoneNumber;
    EditText emailInput;
    EditText website_editText;
    EditText password_editText;
    Spinner categoryList;
    Button orgRegisterButton;

    String orgName, orgMission, address, phone, email, website, password, category;

    String url = "http://madina.mthd.kz/technovation/add_organization.php?";

    String responseGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_signup);
        Intent intent = getIntent();
        String[] arraySpinner = new String[] {
        "Orphans", "Youth", "Elderly people", "Homeless", "Disabled and sick", "War veterans", "Single women", "Local community"};
        Spinner s = (Spinner) findViewById(R.id.categoryList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        orgNameInput = (EditText) findViewById(R.id.orgNameInput);
        orgMissionInput = (EditText)findViewById(R.id.orgMissionInput);
        orgAddress = (EditText) findViewById(R.id.addressInput);
        phoneNumber = (EditText) findViewById(R.id.phoneInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        website_editText =(EditText) findViewById(R.id.websiteInput);
        password_editText = (EditText) findViewById(R.id.passwordInput);
        categoryList = (Spinner) findViewById(R.id.categoryList);
        orgRegisterButton =(Button) findViewById(R.id.organizationRegistrationButton);

        orgRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://madina.mthd.kz/technovation/add_organization.php?";
                orgName = orgNameInput.getText().toString();
                orgMission = orgMissionInput.getText().toString();
                address = orgAddress.getText().toString();
                phone = phoneNumber.getText().toString();
                email = emailInput.getText().toString();
                website = website_editText.getText().toString();
                password = password_editText.getText().toString();
                category = categoryList.getSelectedItem().toString();

                if (orgName.matches("") || orgMission.matches("") || phone.matches("") ||
                        address.matches("") || website.matches("") ||
                        category.matches("") || email.matches("") ||
                        password.matches("")) {
                    Toast.makeText(OrganizationSignUpActivity.this, "Please, fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent1 = new Intent(OrganizationSignUpActivity.this, OrganizationLoginActivity.class);

                    String new_url = "name=" + orgName + "&mission=" + orgMission + "&address=" + address + "&category=" + category + "&email=" + email + "&phone_number=" + phone + "&website=" + website + "&password=" + password;
                        url = url + new_url;
//                    Toast.makeText(OrganizationSignUpActivity.this, url, Toast.LENGTH_LONG).show();

                    final RequestQueue req = Volley.newRequestQueue(OrganizationSignUpActivity.this);

                        StringRequest sreq = new StringRequest(Request.Method.GET, url,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        String result = email+" "+password;
                                        intent1.putExtra("message",result);
                                        startActivity(intent1);
                                        req.stop();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(OrganizationSignUpActivity.this, "Not success", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                                req.stop();
                            }
                        });
                        req.add(sreq);
                }
            }
        });

    }

    public void openLoginPage(View view){
        Intent intent = new Intent(OrganizationSignUpActivity.this, OrganizationLoginActivity.class);
//        intent.putExtra("result", responseGlobal);
        startActivity(intent);
    }
}
