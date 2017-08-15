package com.naturalborncamper.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;
    Button rollButton;

    // Field to hold the score
    int score;

    Random rand;

    int dice1, dice2, dice3;

    ArrayList<Integer> dice;
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Set initial score
        this.score = 0;

        rollResult = (TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        ImageView diceImage1 = (ImageView) findViewById(R.id.dice1Image);
        ImageView diceImage2 = (ImageView) findViewById(R.id.dice2Image);
        ImageView diceImage3 = (ImageView) findViewById(R.id.dice3Image);

        // Create greeting
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        rand = new Random();

        dice = new ArrayList<Integer>();

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(diceImage1);
        diceImageViews.add(diceImage2);
        diceImageViews.add(diceImage3);
    }

    public void rollDice(View v)
    {
        rollResult.setText("Clicked");

        dice1 = rand.nextInt(6)+1;
        dice2 = rand.nextInt(6)+1;
        dice3 = rand.nextInt(6)+1;

        dice.clear();
        dice.add(dice1);
        dice.add(dice2);
        dice.add(dice3);

        for (int diceOfSet=0; diceOfSet<3; diceOfSet++) {
            String imageName = "dice" + dice.get(diceOfSet) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(diceOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String msg = "You rolled a " + dice1 + ", " + dice2 + " and " + dice3;
        rollResult.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
