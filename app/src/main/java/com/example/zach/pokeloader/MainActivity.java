package com.example.zach.pokeloader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Pokemon> mons = new ArrayList<>();
    private RecyclerView recView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        adapter = new MyAdapter(mons);
        recView.setAdapter(adapter);

        for(int i = 1; i < 803; i++) {
            PokeTask pokeTask = new PokeTask(this, i);
            pokeTask.execute();
        }
    }

    private class PokeTask extends AsyncTask<Integer, Integer, Pokemon> {
        private int id;
        private Context cont;
        private String baseURL = "https://pokeapi.co/api/v2/pokemon/";

        public PokeTask(Context c, int x) {
            id = x;
            cont = c;
        }

        @Override
        public void onPreExecute() {}

        @Override
        public Pokemon doInBackground(Integer... params) {
            Pokemon result;

            URL url = null;
            HttpsURLConnection connection = null;
            StringBuffer response = new StringBuffer();
            String json;
            try {
                url = new URL(baseURL + id + "/");
            } catch (MalformedURLException e) {
                Toast.makeText(cont, "Invalid URL", Toast.LENGTH_LONG).show();
            }

            try {
                connection = (HttpsURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0...");
                connection.connect();
                int stat = connection.getResponseCode();
                if(stat != 200) {
                    throw new IOException("Request Failed, code given: " + stat);
                } else {
                    BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while((line = input.readLine()) != null) {
                        response.append(line);
                    }
                    input.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                json = response.toString();
                result = parseJson(json);
            }

            return result;
        }

        @Override
        public void onPostExecute(Pokemon pokemon) {
            mons.add(pokemon);
            adapter.notifyDataSetChanged();
        }

        private Pokemon parseJson(String j) {
            Gson g = new Gson();
            return g.fromJson(j, Pokemon.class);
        }
    }
}
