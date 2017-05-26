package com.example.riadhfarhati.salettuto;

import android.content.Intent;
import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.text.format.Time.getCurrentTimezone;
import static java.util.Calendar.AM_PM;

public class Main22Activity extends AppCompatActivity {
    private DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        startService(new Intent(Main22Activity.this, NotifyingDailyService.class));


        TextView tvfajr=(TextView)findViewById(R.id.tfajr);
        TextView tvchourouk=(TextView)findViewById(R.id.tchourouk);
        TextView tvdoher=(TextView)findViewById(R.id.tdoher);
        TextView tvaser=(TextView)findViewById(R.id.taser);
        TextView tvmagreb=(TextView)findViewById(R.id.tmagreb);
        TextView tvaecha=(TextView)findViewById(R.id.ticha);
        String dateaujorduit="";
        mydb = new DBHelper(this);

        try {

            Calendar calander = Calendar.getInstance();


           // Toast.makeText(getApplicationContext(),"heur"+a,Toast.LENGTH_LONG).show();
           int d= calander.get(Calendar.DAY_OF_MONTH);
          int m=  calander.get(Calendar.MONTH) + 1;
            int y = calander.get(Calendar.YEAR);
            int x = calander.get(Calendar.HOUR);
            int z = calander.get(Calendar.MINUTE);
            int f = calander.get(Calendar.AM_PM);
            String h= Integer.toString(x);
            String l= Integer.toString(z);
            String k= Integer.toString(f);
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
       //     Toast.makeText(getApplicationContext(),"heur"+h+":"+z+"  forma"+k,Toast.LENGTH_LONG).show();
           dateaujorduit=Integer.toString(y)+"-"+Integer.toString(m)+"-"+Integer.toString(d);
           // newDate.toString();  //This will print the Human Rea
          //  Toast.makeText(getApplicationContext(),newDate.toString(),Toast.LENGTH_LONG).show();

         //   Toast.makeText(getApplicationContext(),"  y "+dateaujorduit,Toast.LENGTH_LONG).show();

        }catch (Exception ee){


        }

        try {
            Cursor  cursor=mydb.getData(dateaujorduit);
            cursor.moveToFirst();
            Log.e("ressc", String.valueOf(cursor.getCount()));
            tvfajr.setText(cursor.getString(2));
            tvchourouk.setText(cursor.getString(3));
            tvdoher.setText(cursor.getString(4));
            tvaser.setText(cursor.getString(5));
            tvmagreb.setText(cursor.getString(6));
            tvaecha.setText(cursor.getString(7));
            //s=Data.ViewSalets("2017-5-24");
            if (!cursor.isClosed())  {
                cursor.close();
            }
        }catch (Exception e){

            Log.e("resss",e.toString());
        }

    }
}
