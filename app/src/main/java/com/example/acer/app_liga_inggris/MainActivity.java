package com.example.acer.app_liga_inggris;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> list_klasemen;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adp_klub;
    ProgressDialog loading;

    private RequestQueue requestQueue;
    public final String url = "http://bayuu.net/api/klasemen-premier/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list_klasemen = new ArrayList<Item>();
        requestQueue = Volley.newRequestQueue(this);
        getData();
        adp_klub = new adapter_klub(list_klasemen, this);
        recyclerView.setAdapter(adp_klub);
    }

    public void getData() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    final ProgressDialog loading = ProgressDialog.show(MainActivity.this,"Loading Data", "Please wait...",false,false);
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("KLASEMENT", response.toString());
                        loading.dismiss();
                        try {
                            //JSONObject json = null;
                            String status = response.getString("status");
                            String kompetisi = response.getString("kompetisi");
                            JSONArray klasemen = response.getJSONArray("klasemen");

                            for (int i = 0; i < klasemen.length(); i++) {
                                Item item_klasemen = new Item();
                                JSONObject json = klasemen.getJSONObject(i);
                                item_klasemen.setPosisi(json.getInt("posisi"));
                                item_klasemen.setKlub(json.getString("klub"));
                                item_klasemen.setMain(json.getString("main"));
                                item_klasemen.setMenang(json.getString("menang"));
                                item_klasemen.setSeri(json.getString("seri"));
                                item_klasemen.setKalah(json.getString("kalah"));
                                item_klasemen.setPoin(json.getString("poin"));
                                Log.d("responsenya",String.valueOf(response));
                                System.out.println("ini kesalahannya " + String.valueOf(response));
                                list_klasemen.add(item_klasemen);
                                //Toast.makeText(getApplicationContext(),"pesannnya " + response,Toast.LENGTH_LONG).show();
                            }
                            //adp_klub.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("ini kesalahannya " + e.getMessage());
                        }
                        adp_klub.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ini kesalahannya",error.toString());
                        System.out.println("ini kesalahannya " + error.getMessage());
                        Toast.makeText(getApplicationContext(),"ssilahakn coba lagi", Toast.LENGTH_LONG).show();

                    }
                });
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                3600, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonRequest);
    }
}