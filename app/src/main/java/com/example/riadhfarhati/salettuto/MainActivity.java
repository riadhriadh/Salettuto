package com.example.riadhfarhati.salettuto;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    public String resss = "";
    private DBHelper mydb ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

///////////
        new Thread(new Runnable() {

            int progressBarStatus = 0;//valeur de progress bar

            @Override
            public void run() {


                while (progressBarStatus < 100)        //// itha progress bar mawsells 100 %
                {

                    progressBarStatus = progressBarStatus + 5;   //yzid y9adem b 10

                    ///////////////////////////
                    try {
                        Thread.sleep(500);    ///////ya7bes 1 s////
                    } catch (InterruptedException e) {

                        e.printStackTrace();         //////// yraja3 les erreur
                    }
                    /////////////////////////////////////////


                    ///////////////////////////////////////////////////////////// afficher le etat de progress bar


                }
                /////////////////////////////////////////////////////////// itha fat 100%


                if (progressBarStatus > 90) {

                    GPSTracker gp=new GPSTracker(getApplicationContext());
                    Double la=gp.getLatitude();
                    Double lag=gp.getLongitude();


                    String laa=la.toString();
                    String lagg=lag.toString();
                    Log.e("res21", laa);



                    if (laa !="0.0" ) {
                        new   Rest1(laa,lagg).execute();

                        Intent intent = new Intent(getApplication(),Main2Activity.class);
                        startActivity(intent);


                    }else {
                        Cursor cursor=mydb.getDatainfo();
                        if(cursor.getCount()>0){
                            Intent intent = new Intent(getApplication(),Main22Activity.class);
                            startActivity(intent);

                        }
                        else {


                            la = gp.getLatitude();
                            lag = gp.getLongitude();


                            laa = la.toString();
                            lagg = lag.toString();
                            Log.e("res22", laa);
                            if(laa.equals("0.0"))
                            {


                              // Toast.makeText(getApplicationContext(),"Gps non active",Toast.LENGTH_LONG).show();


                            }
                             if (laa !="0.0") {

                                 Intent intent = new Intent(getApplication(), Main2Activity.class);
                                 startActivity(intent);
                             }
                        }

                    }
                }















            }

        }).start();


    }


    /////////////


    class Rest1 extends AsyncTask<Void, Void, Boolean> {
        String ress = "";





        private ProgressDialog mProgressDialog;
        private JSONArray jsonObjectResult1 = null;


        private String lalti;
        private String lang;

        public Rest1(String lalti, String lang) {
            this.lalti = lalti;
            this.lang=lang;


        }
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectionSql con = new ConnectionSql();


            String[] params = {};

            try {
                ress = con.Post("http://maps.googleapis.com/maps/api/geocode/json?latlng="+lalti+","+lang, params);
                Log.e("res1",ress);

            } catch (ExecutionException e1) {
                Log.e("erreur", e1.toString());
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e("erreur", e.toString());
            }



        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //  JSONObject jsonObj;



            try {
                JSONObject jsonbject = new JSONObject(ress);
                Log.e("res2s","mode traville");
                //JSONArray jsonArray = new JSONArray(ress);
                //  JSONArray jsonArray = jsonbject.getJSONArray(ress);
                Log.e("res1s",jsonbject.toString());
                // Log.e("res1ss",jsonbject.toString());

                try {

                    if (jsonbject !=null) {
                        JSONArray jsonArray= (JSONArray) jsonbject.get("results");
                        String jj1=jsonArray.toString();
                        Log.e("j1",jj1);
                        JSONArray jj= (JSONArray) jsonArray.getJSONObject(0).get("address_components");
                        String jj2= jj.getJSONObject(3).get("short_name").toString();
                        String jj3= jj.getJSONObject(4).get("long_name").toString();

                        Log.e("j2",jj2);
                        Log.e("jjj3",jj3);
                        return true;
                    }if(jsonbject ==null){
                        return  false;

                    }
                    else{
                        return  false;

                    }

                }catch (Exception ex){
                    ex.printStackTrace();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return true;
        }
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);




            if (aBoolean) {









            }
            else{

            }
        }


    }
























}


















