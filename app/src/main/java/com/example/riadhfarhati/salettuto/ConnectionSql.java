package com.example.riadhfarhati.salettuto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;


public class ConnectionSql {
    public static String Get(String s ,String[] a) throws ExecutionException, InterruptedException {

        String parameters = "";

        for (int i = 0; i < a.length; i++) {
            try {
                parameters += a[i] + "=" + URLEncoder.encode(a[i + 1], "UTF-8") + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        return new DownloadWebpageTask().execute(s+"?"+parameters,"GET","").get();
    }

    public static String Post(String s ,String[] a) throws ExecutionException, InterruptedException {

        String parameters ="";

        for(int i=0; i<a.length ;i++){
            try {
                parameters += a[i]+"=" + URLEncoder.encode(a[i+1],"UTF-8")+"&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }


        return new DownloadWebpageTask().execute(s,"POST",parameters).get();

    }

    public static Bitmap Download_Image(String s) throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        return new Download_Image().execute(s).get();

    }
    private static class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... urls) {

// params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0] ,urls[1],urls[2] );
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        }

        private String downloadUrl(String myurl ,String method , String data) throws IOException {
            InputStream is = null;
// Only display the first 500 characters of the retrieved
// web page content.


            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod(method);
                conn.setDoInput(true);
                conn.setRequestProperty("Accept-Encoding", "UTF-8");
                conn.setRequestProperty("Connection", "close");
                if (method == "POST") {
                    conn.setDoOutput(true);

                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    PrintWriter out = new PrintWriter(conn.getOutputStream());
                    out.print(data);
                    out.close();
                }
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                is = conn.getInputStream();

                // Convert the InputStream into a string


                String contentAsString = readIt(is, conn.getContentLength());
                return contentAsString;


                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            int n = 0;
            char[] buffer = new char[1024 * 4];
            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
            return writer.toString();
        }


    }


    private static class Download_Image extends AsyncTask<String, Integer, Bitmap>{
        private static Bitmap downloadUrl(String strUrl) throws IOException{
            Bitmap bitmap=null;
            InputStream iStream = null;
            try{
                URL url = new URL(strUrl);
                /** Creating an http connection to communcate with url */
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                /** Connecting to url */
                urlConnection.connect();

                /** Reading data from url */
                iStream = urlConnection.getInputStream();

                /** Creating a bitmap from the stream returned from the url */
                bitmap = BitmapFactory.decodeStream(iStream);

            }catch(Exception e){

            }finally{
                iStream.close();
            }
            return bitmap;
        }

        Bitmap bitmap = null;
        @Override
        protected Bitmap doInBackground(String... url) {
            try{
                bitmap = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
        }
    }
}
