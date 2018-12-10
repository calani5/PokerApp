package com.example.cmala.pokerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

        while (!start) {

        }

        Intent intent = getIntent();
        numberOfCpus = intent.getIntExtra("numberCpus", 1);
        Player player = new Player(deck.draw(), deck.draw());
        if (numberOfCpus == 1) {
            setContentView(R.layout.one_cpugame_screen);
            CPU bot1 = new CPU(deck.draw(), deck.draw());
        } else if (numberOfCpus == 2) {
            setContentView(R.layout.two_cpugame_screen);
            CPU bot1 = new CPU(deck.draw(), deck.draw());
            CPU bot2 = new CPU(deck.draw(), deck.draw());
        } else {
            setContentView(R.layout.three_cpugame_screen);
            CPU bot1 = new CPU(deck.draw(), deck.draw());
            CPU bot2 = new CPU(deck.draw(), deck.draw());
            CPU bot3 = new CPU(deck.draw(), deck.draw());
        }

    }

    public static void setUp() {
        start = true;
    }

    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://deckofcardsapi.com/api/deck/" + dID + "/draw/?count=52",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            deck.createDeck(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
