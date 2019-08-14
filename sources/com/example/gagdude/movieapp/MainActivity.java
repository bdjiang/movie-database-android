package com.example.gagdude.movieapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.LinearLayoutManager;
import android.support.p003v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Movie> mMovies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    public MovieAdapter movieAdapter;
    final int pages = 5;
    private FloatingActionButton webButton;

    public class MoviesAsyncTask extends AsyncTask<URL, Void, Movie[]> {
        Context context;

        public MoviesAsyncTask(Context context2) {
            this.context = context2;
        }

        public Movie[] doInBackground(URL... params) {
            try {
                URLConnection connection = params[0].openConnection();
                connection.connect();
                return ((MovieCollection) new Gson().fromJson((Reader) new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")), MovieCollection.class)).getResults();
            } catch (IOException e) {
                Log.d("MoviesAsyncTask", "failed to get data from network", e);
                return null;
            }
        }

        public void onPostExecute(Movie[] movies) {
            if (movies == null) {
                Toast.makeText(this.context, "Failed to get network data", 1).show();
                return;
            }
            Collections.addAll(MainActivity.this.mMovies, movies);
            MainActivity.this.movieAdapter.notifyDataSetChanged();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0392R.layout.activity_main);
        this.mRecyclerView = (RecyclerView) findViewById(C0392R.C0394id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.movieAdapter = new MovieAdapter(this.mMovies);
        this.mRecyclerView.setAdapter(this.movieAdapter);
        for (int requests = 1; requests <= 5; requests++) {
            try {
                URL url = new URL(Strings.urlPlusAPI + requests);
                new MoviesAsyncTask(this).execute(new URL[]{url});
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        this.webButton = (FloatingActionButton) findViewById(C0392R.C0394id.webButton);
        this.webButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent("android.intent.action.VIEW");
                browserIntent.setData(Uri.parse("https://www.themoviedb.org/"));
                MainActivity.this.startActivity(browserIntent);
            }
        });
    }
}
