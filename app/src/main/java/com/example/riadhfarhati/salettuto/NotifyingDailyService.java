package com.example.riadhfarhati.salettuto;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by i on 25/05/2017.
 */

public class NotifyingDailyService extends Service {
    private DBHelper mydb ;
    AudioManager myAudioManager;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent pIntent, int flags, int startId) {
        final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.athan);
        // TODO Auto-generated method stub
        mydb = new DBHelper(this);

        //////////
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    String taime="";
                    String l="";
                    Calendar calander = Calendar.getInstance();
                    int d= calander.get(Calendar.DAY_OF_MONTH);
                    int m=  calander.get(Calendar.MONTH) + 1;
                    int y = calander.get(Calendar.YEAR);
                   // int x = calander.get(Calendar.HOUR_OF_DAY);
                   //  String h= Integer.toString(x);



                    int x = calander.get(Calendar.HOUR);
                    int z = calander.get(Calendar.MINUTE);
                    int f = calander.get(Calendar.AM_PM);

                    if(z<10){
                        l= Integer.toString(z);
                        l="0"+l;

                    }else{
                        l= Integer.toString(z);

                    }
                    String h= Integer.toString(x);

                    String k= Integer.toString(f);
                    if(f==0){
                        taime=h+":"+l+" am";

                    }
                    if(f==1){
                         taime=h+":"+l+" pm";

                    }

                  ///  Toast.makeText(getApplicationContext(),"heur"+taime,Toast.LENGTH_LONG).show();
                  String  dateaujorduit=Integer.toString(y)+"-"+Integer.toString(m)+"-"+Integer.toString(d);
                    // newDate.toString();  //This will print the Human Rea
                    //  Toast.makeText(getApplicationContext(),newDate.toString(),Toast.LENGTH_LONG).show();

                   // Toast.makeText(getApplicationContext(),"  y "+dateaujorduit,Toast.LENGTH_LONG).show();

                    Log.e("startservice","yesssssss");
                  //  Toast.makeText(getApplicationContext(),h,Toast.LENGTH_LONG).show();

                    try {
                        Cursor  cursor=mydb.getData(dateaujorduit);
                        cursor.moveToFirst();
                        Log.e("ressc", String.valueOf(cursor.getCount()));
                         String  fajr =cursor.getString(2);
                        String chourouk=cursor.getString(3);
                        String doher=cursor.getString(4);
                        String aser=cursor.getString(5);
                        String magreb=cursor.getString(6);
                        String echa=cursor.getString(7);

                        if (!cursor.isClosed())  {
                            cursor.close();
                        }
                        if(taime.equals(fajr)){

                            myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                            mPlayer.start();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                            builder.setTitle("حان موعد الصلاة");
                            builder.setMessage("صلاة الفجر");
                            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Do something
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("غلق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    mPlayer.stop();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alert.show();





                        }
                        if(taime.equals(doher)){

                            mPlayer.start();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                            builder.setTitle("حان موعد الصلاة");
                            builder.setMessage("صلاة الظهر");
                            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Do something
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("غلق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mPlayer.stop();
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alert.show();




                        }
                        if(taime.equals(aser)){


                            mPlayer.start();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                            builder.setTitle("حان موعد الصلاة");
                            builder.setMessage("صلاة العصر");
                            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Do something
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("غلق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mPlayer.stop();
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alert.show();

                        }
                        if(taime.equals(magreb)){


                            mPlayer.start();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                            builder.setTitle("حان موعد الصلاة");
                            builder.setMessage("صلاة المغرب");
                            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Do something
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("غلق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mPlayer.stop();
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alert.show();


                        }
                        if(taime.equals(echa)){

                            mPlayer.start();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                            builder.setTitle("حان موعد الصلاة");
                            builder.setMessage("صلاة العشاء");
                            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Do something
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("غلق", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mPlayer.stop();
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alert.show();


                        }





                    }catch (Exception e){

                        Log.e("resss",e.toString());
                    }





                }catch (Exception ee) {

                }

                handler.postDelayed(this, 50000);
            }
        }, 12000);












        //////////////////












        Toast.makeText(this, "NotifyingDailyService", Toast.LENGTH_LONG).show();



        Calendar calander = Calendar.getInstance();
        int d= calander.get(Calendar.DAY_OF_MONTH);
        int m=  calander.get(Calendar.MONTH) + 1;
        int y = calander.get(Calendar.AM_PM);
      //  Log.i("com.example.bootbroadcastpoc","NotifyingDailyService");

        return super.onStartCommand(pIntent, flags, startId);
    }
}