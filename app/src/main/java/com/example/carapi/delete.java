package com.example.carapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class delete extends AppCompatActivity {

    EditText editText;
    Button btngo;
    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        txt1= findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        btngo=findViewById(R.id.btngo);
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


            editText = findViewById(R.id.editText);
            @SuppressLint("WrongThread") String val=(editText.getText().toString());


            try {

                urL = new URL("http://192.168.56.1:8080/CAR_sharing/car/mad/deletecardetails&" + val);
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
                ou1 = obj.getString("carid");
                ou2 = obj.getString("message :");
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

            txt1.setText(ou1);
            txt2.setText(ou2);

            super.onPostExecute(result);
        }
    }
}
