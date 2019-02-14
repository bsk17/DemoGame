package com.example.hp.demogame;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // for multi-player
    int currentPlayer = 1 ;

    // this variable will help in stopping the game-play once a player wins
    boolean gamePlay = true;

    // to maintain record of what have been selected
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //this is an array which consists of positions where a player can win
    int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    // this is the function associated with the image
    // when the image is clicked then this function gets active
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        // to identify which image-view is pressed int the grid
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // to make sure that once clicked then again it cannot be clicked
        // gamePlay will make sure that it does not run after winning
        if(gameState[tappedCounter] == 2  && gamePlay) {

            // to keep record of what is being inserted into the image by array
            gameState[tappedCounter] = currentPlayer;

            // to remove the image in the beginning of the app
            counter.setTranslationY(-1500);

            // this if-else is to change the tock or cross accordingly
            if (currentPlayer == 1) {

                counter.setImageResource(R.drawable.tick);
                currentPlayer = 0;
            } else {
                counter.setImageResource(R.drawable.cross);
                currentPlayer = 1;
            }

            // to bring the icon in style
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);

            // now we create a loop to check the winning positions of the game state which is an array of array
            for (int[] winpos : winpos) {
                // checking condition
                if (gameState[winpos[0]] == gameState[winpos[1]] && gameState[winpos[1]] == gameState[winpos[2]] && gameState[winpos[0]] != 2) {

                    // to print the message of winning
                    String winner ;

                    //to stop the game after a player wins
                    gamePlay = false ;

                    if (currentPlayer == 0) {
                        winner = "PLAYER 1";
                    } else {
                        winner = "PLAYER 2";
                    }

                    // this will enable the button and text view once a player wins
                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    // to print who is the winner
                    winnerText.setText(winner + " has won ");

                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);

                    // this is for the animation gif
                    ImageView party = (ImageView)findViewById(R.id.partyGifImageView);


                }
            }
        }
    }

    // this function will get active when we click the PLAY AGAIN button
    public void playAgain(View view){
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        // getting error here
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.mainGridLayout);

        // this is to create child process of our grid layout
        for (int i=0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            // this will clear all the images in the grid layout
            counter.setImageDrawable(null);

        }

        // we have to set default all once again except the winning positions because they wont change ever
        currentPlayer = 1 ;

        gamePlay = true;

        // to update the array entries
        for(int i= 0; i<gameState.length; i++){
            gameState[i] = 2 ;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
