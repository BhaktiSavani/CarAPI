package com.example.carapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class insert extends AppCompatActivity {

    EditText txt1,txt2,txt3,txt4,txt5,txt6;
    TextView msg;
    Button btngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btngo=findViewById(R.id.btngo);
        msg=findViewById(R.id.txtmsg);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }
    private class MyTask extends AsyncTask<Void,Void,Void> {
        String ou1, ou2, ou3, ou4;

        @Override
        protected Void doInBackground(Void... params) {

            URL urL = null;


            txt1 = findViewById(R.id.txt1);
            @SuppressLint("WrongThread") String val = (txt1.getText().toString());
            txt2=findViewById(R.id.txt2);
            @SuppressLint("WrongThread") String val2 = (txt2.getText().toString());
            txt3=findViewById(R.id.txt3);
            @SuppressLint("WrongThread") String val3 = (txt3.getText().toString());
            txt4=findViewById(R.id.txt4);
            @SuppressLint("WrongThread") String val4 = (txt4.getText().toString());
            txt5=findViewById(R.id.txt5);
            @SuppressLint("WrongThread") String val5 = (txt5.getText().toString());
            txt6=findViewById(R.id.txt6);
            @SuppressLint("WrongThread") String val6 = (txt6.getText().toString());


            try {

                urL = new URL("http://192.168.56.1:8080/CAR_sharing/car/mad/carregistration&" + val +"&"+ val2 +"&"+ val3+"&"+val4+"&"+val5+"&"+val6);
                HttpURLConnection client = null;
                client = (HttpURLConnection) urL.openConnection();
                client.setRequestMethod("GET");
                int responceCode = client.getResponseCode();
                System.out.println("sending 'GET'request to url :" + urL);
                System.out.println("response code:" + responceCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputline;
                StringBuffer response = new StringBuffer();
                while ((inputline = in.readLine()) != null) {
                    response.append(inputline);

                }
                in.close();
                System.out.println(response.toString());

                JSONObject obj = new JSONObject(response.toString());
                ou1 = obj.getString("message :");
             //   ou2 = obj.getString("message");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }
        @Override
        protected void onPostExecute(Void result) {

            msg.setText(ou1);


            super.onPostExecute(result);
        }
    }
}
