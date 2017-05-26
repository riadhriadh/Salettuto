package com.example.riadhfarhati.salettuto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity {
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb = new DBHelper(this);


        ////////////
        new Thread(new Runnable() {

            int progressBarStatus = 0;//valeur de progress bar

            @Override
            public void run() {
                new   Rest1("df","fd").execute();

                while (progressBarStatus < 100)        //// itha progress bar mawsells 100 %
                {

                    progressBarStatus = progressBarStatus + 10;   //yzid y9adem b 10

                    ///////////////////////////
                    try {
                        Thread.sleep(200);    ///////ya7bes 1 s////
                    } catch (InterruptedException e) {

                        e.printStackTrace();         //////// yraja3 les erreur
                    }
                    /////////////////////////////////////////


                    ///////////////////////////////////////////////////////////// afficher le etat de progress bar


                }
                /////////////////////////////////////////////////////////// itha fat 100%


                if (progressBarStatus > 90) {


                    Intent i=new Intent(getApplicationContext(),Main22Activity.class);
                    startActivity(i);
                }















            }

        }).start();


    }











    ////////////////













    private class Rest1 extends AsyncTask<Void, Void, Boolean> {
        String ress = "";



        //   Toast.makeText(getApplicationContext(), ress, Toast.LENGTH_LONG).show();


        // http://boitedenuit.coolpage.biz/listeven.php

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
            //Http Get Method
            try {
                ress = con.Post("http://muslimsalat.com/Tunisie+zaghouan/yearly.json?key=e6d5c44486ede2d1fd2c5ad46ef81ca6", params);
                Log.e("res1",ress);
               // Toast.makeText(getApplicationContext(),ress.toString(),Toast.LENGTH_LONG).show();
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

                //JSONArray jsonArray = new JSONArray(ress);
                //  JSONArray jsonArray = jsonbject.getJSONArray(ress);
                Log.e("res1s",jsonbject.toString());
                // Log.e("res1ss",jsonbject.toString());

                try {

                    if (jsonbject !=null) {
                        JSONArray jsonArray= (JSONArray) jsonbject.get("items");
                        String jj1=jsonArray.toString();
                        Log.e("j1",jj1);
                       // JSONArray jj= (JSONArray) jsonArray.getJSONObject(0).get("address_components");
                       // String jj2= jj.getJSONObject(3).get("short_name").toString();

                      //  Toast.makeText(getApplicationContext(),jj1,Toast.LENGTH_LONG).show();
                       // Log.e("j2",jj2);

                      //  String dda=jsonObjectt.getString("date_for").toString();

                      //  Log.e("listdate",jsona.toString());
                        Log.e("listdate1","non data");
                        Log.e("l1",jj1);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObjectt= (JSONObject) jsonArray.get(i);
                            String date=jsonObjectt.getString("date_for").toString();

                            String fajr=jsonObjectt.getString("fajr").toString();
                            String shurooq=jsonObjectt.getString("shurooq").toString();
                            String dhuhr=jsonObjectt.getString("dhuhr").toString();

                            String asr=jsonObjectt.getString("asr").toString();
                            String maghrib=jsonObjectt.getString("maghrib").toString();
                            String isha=jsonObjectt.getString("isha").toString();
                             Boolean a= mydb.insertSalets(date,fajr,shurooq,dhuhr,asr,maghrib,isha);



                            Log.e("listdate",a.toString());



                        }

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
