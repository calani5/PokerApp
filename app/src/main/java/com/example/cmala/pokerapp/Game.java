package com.example.cmala.pokerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Game extends AppCompatActivity {
    private int numberOfCpus = 1;
    private static final String TAG = "Game";
    private static RequestQueue requestQueue;
    Deck deck = new Deck();
    static boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        startAPICall();

        Intent intent = getIntent();
        numberOfCpus = intent.getIntExtra("numberCpus", 1);
        final Player player = new Player(deck.draw(), deck.draw());
        final CPU bot1 = new CPU(deck.draw(), deck.draw());
        final CPU bot2 = new CPU(deck.draw(), deck.draw());
        final CPU bot3 = new CPU(deck.draw(), deck.draw());
        if (numberOfCpus == 1) {
            setContentView(R.layout.one_cpugame_screen);
        } else if (numberOfCpus == 2) {
            setContentView(R.layout.two_cpugame_screen);

        } else {
            setContentView(R.layout.three_cpugame_screen);
        }

        final Button hit = findViewById(R.id.button2);
        final Button stay = findViewById(R.id.button3);
        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addCard(deck.draw());
                player.update();
                if (bot1.cpuUpdate()) {
                    bot1.cpuUpdate();
                }
                if (numberOfCpus == 2 && bot2.cpuUpdate()) {
                    bot2.cpuUpdate();
                }
                if (numberOfCpus == 3) {
                    if (bot2.cpuUpdate()) {
                        bot2.cpuUpdate();
                    }
                    if (bot3.cpuUpdate()) {
                        bot3.cpuUpdate();
                    }
                }
            }
        });
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bot1.cpuUpdate()) {
                    bot1.cpuUpdate();
                } else {
                    //gameover
                }
                if (numberOfCpus == 2 && bot2.cpuUpdate()) {
                    bot2.cpuUpdate();
                } else if (!bot1.cpuUpdate() && !bot2.cpuUpdate()) {
                    //gameover
                }
                if (numberOfCpus == 3) {
                    if (bot2.cpuUpdate()) {
                        bot2.cpuUpdate();
                    }
                    if (bot3.cpuUpdate()) {
                        bot3.cpuUpdate();
                    } else if(!bot1.cpuUpdate() && !bot2.cpuUpdate() && !bot3.cpuUpdate()) {
                        //gameover
                    }
                }

            }
        });

    }

    public static void setUp() {
        start = true;
    }

    void startAPICall() {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest("https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1", new JSONObject(), future, future);
        requestQueue.add(request);
        try {
            JSONObject response = future.get(60, TimeUnit.SECONDS);
            apiCallDone(response);// this will block
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        } catch (TimeoutException e) {
            // exception handling
        }
    }
    void apiCallDone(final JSONObject response) {
        try {
            deck.setDeckID(response.getString("deck_id"));
            System.out.print(response.getString("deck_id"));
            drawCards(deck.getDeckID());
        } catch (JSONException ignored) {
            System.out.print("shit");
        }
    }

    void drawCards(String dID) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest("https://deckofcardsapi.com/api/deck/" + dID + "/draw/?count=52", new JSONObject(), future, future);
        requestQueue.add(request);
        try {
            JSONObject response = future.get(60, TimeUnit.SECONDS);
            deck.createDeck(response);// this will block
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        } catch (TimeoutException e) {
            // exception handling
        }
    }

    public int getNumberOfCpus() {
        return numberOfCpus;
        //fd
    }
}
