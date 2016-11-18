package com.example.floris.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText translateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTranslateClick(View view) {

        EditText translateEditText = (EditText) findViewById(R.id.editText);

        if (!isEmpty(translateEditText)) {

            Toast.makeText(this, "Getting Translateions",
                    Toast.LENGTH_LONG).show();

            Intent getNameScreenIntent = new Intent(this, SecondActivity.class);


            new SaveTheFeed().execute();

        } else {

            Toast.makeText(this, "Enter words to translate",
                    Toast.LENGTH_LONG).show();

        }

    }

    protected boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;

    }

    public class SaveTheFeed extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://omdbapi.com/?t=frozen");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            //Do something with the JSON
            

        }

    }
}





//            wordsToTranslate = wordsToTranslate.replace(" ", +);
//
//            StringBuilder result = new StringBuilder();
//
//            try {
//                URL url = new URL("http://www.omdbapi.com/?t=" + wordsToTranslate);
//                URLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    result.append(line);
//                }
//
//            }catch( Exception e) {
//                e.printStackTrace();
//            }
//            finally {
//                urlConnection.disconnect();
//            }

//            return result.toString();

//            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());




//            return null;








