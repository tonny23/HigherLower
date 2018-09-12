package com.example.higherlower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.btnHigher)
    FloatingActionButton btnHigher;

    @BindView(R.id.btnLower)
    FloatingActionButton btnLower;

    @BindView(R.id.listScore)
    ListView listScore;

    @BindView(R.id.tvHighscore)
    TextView tvHighscore;

    @BindView(R.id.tvScore)
    TextView tvScore;

    @BindView(R.id.ivDice)
    ImageView ivDice;

    private int score = 0;
    private int highscore = 0;
    private int randomDice;
    private int prevRandomDice;
    private Random rand = new Random();
    private List<DiceThrow> mDiceThrows;
    private ArrayAdapter mAdapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        view = findViewById(R.id.main_layout);

        mDiceThrows = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDiceThrows);
        listScore.setAdapter(mAdapter);

        randomDice = rand.nextInt(6) + 1;
        prevRandomDice = randomDice;
        setDiceImage();

        tvHighscore.setText(getString(R.string.highscore) + " " + highscore);
        tvScore.setText(getString(R.string.score) + " " + score);

    }

    /**
     * When the lower button is pressed a new random number between 1 and 6 is generated,
     * if the previous dice number is higher than the new generated number the score gets added with one
     * and the highscore gets set if the score is higher than the previous highscore
     * if the previous dice number is lower than the new generated number the score gets set to zero
     * also the listview is updated with the previous throw
     */
    @OnClick(R.id.btnLower)
    public void pressLower() {
        randomDice = rand.nextInt(6) + 1;
        Log.d(TAG, "pressLower: " + randomDice);
        setDiceImage();
        mDiceThrows.add(new DiceThrow(getString(R.string.throw_is) + " " + randomDice));
        mAdapter.notifyDataSetChanged();
        if (randomDice < prevRandomDice) {
            setWinScore();
            // if the new dice number is the same as the previous number nothing will be done
        } else if (randomDice == prevRandomDice) {
            setDraw();
        } else {
            setLoseScore();
        }
        prevRandomDice = randomDice;
    }

    /**
     * When the higher button is pressed a new random number between 1 and 6 is generated,
     * if the previous dice number is lower than the new generated number the score gets added with one
     * and the highscore gets set if the score is higher than the previous highscore
     * if the previous dice number is higher than the new generated number the score gets set to zero
     * also the listview is updated with the previous throw
     */
    @OnClick(R.id.btnHigher)
    public void pressHigher() {
        randomDice = rand.nextInt(6) + 1;
        Log.d(TAG, "pressLower: " + randomDice);
        setDiceImage();
        mDiceThrows.add(new DiceThrow(getString(R.string.throw_is) + " " + randomDice));
        mAdapter.notifyDataSetChanged();
        if (randomDice > prevRandomDice) {
            setWinScore();
            // if the new dice number is the same as the previous number nothing will be done
        } else if (randomDice == prevRandomDice) {
            setDraw();
        } else {
            setLoseScore();
        }
        prevRandomDice = randomDice;
    }

    /**
     * In this method the score gets added with one, a snackbar will be shown and the highscore will be
     * set if the score is higher than the previous highscore
     */
    public void setWinScore() {
        score++;
        tvScore.setText(getString(R.string.score) + score);
        Snackbar
                .make(view, "You win", Snackbar.LENGTH_SHORT)
                .show();
        if (score > highscore) {
            highscore++;
            tvHighscore.setText(getString(R.string.highscore)+ " " + highscore);
        }
    }

    /**
     * In this method the score gets set to zero and a snackbar will be shown
     */
    public void setLoseScore() {
        score = 0;
        tvScore.setText(getString(R.string.score) + " " + score);
        Snackbar
                .make(view, "You lose", Snackbar.LENGTH_SHORT)
                .show();
    }

    /**
     * A snackbar will be shown if it is a draw
     */
    public void setDraw() {
        Snackbar
                .make(view, "Draw", Snackbar.LENGTH_SHORT)
                .show();
    }

    /**
     * Based on the random dice number the image will be set to show the correct dice image
     */
    public void setDiceImage() {
        Log.d(TAG, "setDiceImage: " + randomDice);
        if (randomDice == 1) {
            ivDice.setImageResource(R.drawable.d1);
        } else if (randomDice == 2) {
            ivDice.setImageResource(R.drawable.d2);
        } else if (randomDice == 3) {
            ivDice.setImageResource(R.drawable.d3);
        } else if (randomDice == 4) {
            ivDice.setImageResource(R.drawable.d4);
        } else if (randomDice == 5) {
            ivDice.setImageResource(R.drawable.d5);
        } else if (randomDice == 6) {
            ivDice.setImageResource(R.drawable.d6);
        }
    }
}
