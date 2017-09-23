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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String email,password;
    Button btnLogin,btnReg;
    EditText edtEmail,edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=(Button)findViewById(R.id.logbutton);
        btnReg=(Button)findViewById(R.id.resetbutton);
        edtEmail=(EditText) findViewById(R.id.logemail);
        edtPass=(EditText)findViewById(R.id.logpassword);
    }
    public void LoginProcess(View view) {

        EditText eml = (EditText)findViewById(R.id.logemail);
        EditText pass = (EditText)findViewById(R.id.logpassword);

        email = eml.getText().toString();
        password = pass.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://pawandevelopers.000webhostapp.com/pwnlogin.php";

        StringRequest stringRequest = new StringRequest
                (
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                handleRegisterNetworkResponse(response);
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
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void handleRegisterNetworkResponse(String responseString)
    {
        Toast.makeText(this, responseString, Toast.LENGTH_LONG).show();

        try
        {
            JSONObject response = new JSONObject(responseString);

            String success = response.getString("success");
            String remark = response.getString("remark");

            if(success.equals("0"))
            {
                Toast.makeText(this, "Login Failed\n" + remark, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Login Success\n" + remark, Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "bad response from the sever", Toast.LENGTH_SHORT).show();
        }
    }

    public void sinupProcess(View view) {
        Intent in=new Intent(getApplicationContext(),RegisterOn.class);
        startActivity(in);
        finish();
    }
}
