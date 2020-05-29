package com.example.laboratorio3.datasource;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnection extends AsyncTask<String, String, String> {

    private String apiUrl;
    public AsyncResponse delegate = null;



    public NetworkConnection(String url,AsyncResponse delegate){

        this.apiUrl=url;
        this.delegate=delegate;
    }
    public NetworkConnection(){


    }

    public void setUrl(String url){
        this.apiUrl=url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... params) {
        String response="";
        try {
            URL url;
            HttpURLConnection urlConnection = null;


            try {
                url = new URL(this.apiUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");


                switch (params[0]){
                    case GET:{
                        urlConnection.setRequestMethod("GET");

                    }
                    break;

                    case POST:
                    {
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setRequestProperty("Accept", "application/json");
                        urlConnection.setDoOutput(true);
                        String jsonString=params[1];
                        try(OutputStream os = urlConnection.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }
                    }
                    break;
                    case PUT:
                    {
                        urlConnection.setRequestMethod("PUT");
                        urlConnection.setRequestProperty("Accept", "application/json");
                        urlConnection.setDoOutput(true);
                        String jsonString=params[1];
                        try(OutputStream os = urlConnection.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }
                    }
                    break;

                    case DELETE:
                        {
                            urlConnection.setRequestMethod("DELETE");
                        }
                    break;
                }


                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    response += (char) data;
                    data = isw.read();
                }
                return  response;

            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

           @Override
           protected void onPostExecute(String s) {
               delegate.processFinish(s);

           }



    public final static String GET="GET";
    public final static String POST="POST";
    public final static String DELETE="DELETE";
    public final static String PUT="PUT";

}
