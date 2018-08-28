package com.chait.connect3;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow,1=red
    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 = unplayed
    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin (View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter]==2 && gameIsActive) {
            gamestate[tappedcounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.green_counter);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_counter);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winingPosition : winningPositions) {
                if (gamestate[winingPosition[0]] == gamestate[winingPosition[1]] &&
                        gamestate[winingPosition[1]]==gamestate[winingPosition[2]] &&
                        gamestate[winingPosition[0]] != 2) {
                    //Someone has won !
                    gameIsActive = false;
                    String winner = "Red";
                    if (gamestate[winingPosition[0]] == 0) {
                        winner = "Green";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has WON!!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for(int counterState : gamestate) {
                        if (counterState ==2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i=0;i<gamestate.length;i++) {
            gamestate[i]=2;
        }
        ImageView counter0 = (ImageView) findViewById(R.id.imageView0);
        counter0.setImageResource(0);
        ImageView counter1 = (ImageView) findViewById(R.id.imageView1);
        counter1.setImageResource(0);
        ImageView counter2 = (ImageView) findViewById(R.id.imageView2);
        counter2.setImageResource(0);
        ImageView counter3 = (ImageView) findViewById(R.id.imageView3);
        counter3.setImageResource(0);
        ImageView counter4 = (ImageView) findViewById(R.id.imageView4);
        counter4.setImageResource(0);
        ImageView counter5 = (ImageView) findViewById(R.id.imageView5);
        counter5.setImageResource(0);
        ImageView counter6 = (ImageView) findViewById(R.id.imageView6);
        counter6.setImageResource(0);
        ImageView counter7 = (ImageView) findViewById(R.id.imageView7);
        counter7.setImageResource(0);
        ImageView counter8 = (ImageView) findViewById(R.id.imageView8);
        counter8.setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
