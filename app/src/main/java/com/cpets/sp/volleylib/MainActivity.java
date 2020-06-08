package com.cpets.sp.volleylib;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button click;
    private TextView show;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=(Button)findViewById(R.id.click);
        show=(TextView)findViewById(R.id.show);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myurl="https://api.myjson.com/bins/1617y8";
                 requestQueue= Volley.newRequestQueue(MainActivity.this);
                stringRequest=new StringRequest(Request.Method.GET, myurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String idno=jsonObject.getString("idno");
                            String name=jsonObject.getString("name");
                            String status=jsonObject.getString("status");
                            show.setText(idno +"/"+name+"/"+status);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Sowmthing Went Wrong.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        requestQueue.add(stringRequest);
    }
}
