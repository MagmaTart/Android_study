package com.magmatart.httpurlconnection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private URL Url;
    private String strUrl, strCookie, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                strUrl = "https://github.com/JoMingyu/Ccomet-Howmuch-ExchangeRate";    //탐색할 URL
            }

            @Override
            protected Void doInBackground(Void... voids){
                try{
                    Url = new URL(strUrl);
                    HttpURLConnection connection = (HttpURLConnection)Url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setDefaultUseCaches(false);

                    strCookie = connection.getHeaderField("Set-Cookie");

                    InputStream is = connection.getInputStream();

                    StringBuilder builder = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line;

                    while((line = reader.readLine()) != null)
                        builder.append(line + "\n");

                    result = builder.toString();
                }catch(MalformedURLException | ProtocolException exception){
                    exception.printStackTrace();
                }catch(IOException io){
                    io.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                System.out.println(result);
            }
        }.execute();
    }
}
