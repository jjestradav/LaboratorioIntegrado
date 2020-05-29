package com.example.laboratorio3.datasource;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

public class Dao {

    NetworkConnection con=new NetworkConnection();

    private  static Dao instance=null;
    private Dao(){

    }
    public static Dao getInstance(){
        return instance==null? instance=new Dao() : instance;
    }

    public String login(String username, String password){
        JSONObject obj= new JSONObject();
        try {
            //final String[] result = {""};
            obj.put("username", username);
            obj.put("password", password);

            con.setUrl("http://192.168.0.14:9090/Lab1/login");
            NetworkConnection connection= new NetworkConnection("http://192.168.0.14:9090/Lab1/login", new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    Log.v("RESULT POST",output);
                }
            });
           String result= connection.execute(NetworkConnection.POST,obj.toString()).get();
                return result.isEmpty()?null:result;


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

}



