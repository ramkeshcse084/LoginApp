package com.nestedmango.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterOn extends AppCompatActivity {
    EditText nm,mob,eml,adrs,pincode,pass;
    RadioButton r1,r2;
    RadioGroup rg;
    Button sm,rs;
    String name,mobile,email,password,address,pin,male,female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_on);

        initView();
    }

    private void initView() {
        nm=(EditText)findViewById(R.id.regUname);
        mob=(EditText)findViewById(R.id.regcontactNo);
        eml=(EditText)findViewById(R.id.regemail);
        adrs=(EditText)findViewById(R.id.regaddress);
        pincode=(EditText)findViewById(R.id.regpincode);
        rg=(RadioGroup)findViewById(R.id.rg);
        r1=(RadioButton)findViewById(R.id.male);
        r2=(RadioButton)findViewById(R.id.female);
        sm=(Button)findViewById(R.id.rsubmit);
        rs=(Button)findViewById(R.id.rreset);
        pass=(EditText)findViewById(R.id.regpassword);

    }

    public void startRegisterProcess(View view)
    {
        // EditText user = (EditText)findViewById(R.id.username);
        // EditText pass = (EditText)findViewById(R.id.password);
        // EditText  nm=(EditText)findViewById(R.id.regUname);

        name = nm.getText().toString();
        mobile = mob.getText().toString();
        email = eml.getText().toString();
        password = pass.getText().toString();
        address = adrs.getText().toString();
        pin = pincode.getText().toString();
        male = r1.getText().toString();
        female = r2.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://pawandevelopers.000webhostapp.com/pwnregister.php";



        queue.start();






        StringRequest stringRequest = new StringRequest
                (
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(RegisterOn.this, response, Toast.LENGTH_SHORT).show();
                                //handleRegisterNetworkResponse(response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String,String>();
                params.put("name",name);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("password",password);
                params.put("address",address);
                params.put("pin",pin);
                params.put("gender",male);
                params.put("gender",female);
                // params.put("password",female);
                return params;
            }
        };

        queue.add(stringRequest);
    }

    public void ClearProcess(View view) {
        nm.setText("");
        mob.setText("");
        eml.setText("");
        pass.setText("");
        adrs.setText("");
        pincode.setText("");
    }

    public void regbackProcess(View view) {
        Intent i = new Intent(RegisterOn.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
