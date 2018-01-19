package com.demouser.flytrapsandroses;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button resetButton;
    ImageButton A1, A2, A3, B1, B2, B3, C1, C2, C3;
    Random random = new Random();
    TextView textView, livesView;
    private ArrayList<ImageButton> buttonsList = new ArrayList();
    private HashMap<ImageButton, Integer> buttonsMap = new HashMap();
    private final int NUM_TRAPS = 3;
    private final int MAX_LIVES = 2;
    private int num_lives = MAX_LIVES;
    private int max_roses = 9 - NUM_TRAPS;
    private int roses_clicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.resetButton);
        buttonsList.add(A1 = findViewById(R.id.A1));
        buttonsList.add(A2 = findViewById(R.id.A2));
        buttonsList.add(A3 = findViewById(R.id.A3));
        buttonsList.add(B1 = findViewById(R.id.B1));
        buttonsList.add(B2 = findViewById(R.id.B2));
        buttonsList.add(B3 = findViewById(R.id.B3));
        buttonsList.add(C1 = findViewById(R.id.C1));
        buttonsList.add(C2 = findViewById(R.id.C2));
        buttonsList.add(C3 = findViewById(R.id.C3));
        textView = findViewById(R.id.textView);
        livesView = findViewById(R.id.livesView);

        randomizeButtons();

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < buttonsList.size(); i++) {
                    buttonsList.get(i).setEnabled(true);
                }
                num_lives = MAX_LIVES;
                roses_clicked = 0;
                textView.setText("");
                livesView.setText("Lives: " + num_lives);
                randomizeButtons();
            }
        });

        A1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(A1);
            }
        });

        A2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(A2);
            }
        });

        A3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(A3);
            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(B1);
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(B2);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(B3);
            }
        });
        C1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(C1);
            }
        });
        C2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(C2);
            }
        });
        C3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if_clicked(C3);
            }
        });
    }

    private void randomizeButtons() {

        for (int i = 0; i < buttonsList.size(); i++) {
            // initialize hashmap with all roses
            buttonsMap.put(buttonsList.get(i), 0);
            buttonsList.get(i).setImageResource(R.drawable.unclicked);
        }

        int r;
        ArrayList<Integer> exclude = new ArrayList();
        for (int i = 0; i < NUM_TRAPS; i++) {
            // randomly place traps
            r = getRandomWithExclusion(random, 0, 8, exclude);
            exclude.add(r);
            ImageButton trapButton = buttonsList.get(r);
            buttonsMap.put(trapButton, 1);
        }
    }

    public int getRandomWithExclusion(Random rnd, int start, int end, ArrayList<Integer> exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.size());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public void if_clicked(ImageButton img_button){
        img_button.setEnabled(false);
        if (buttonsMap.get(img_button) == 1){
            img_button.setImageResource(R.drawable.flytrap);
            num_lives -= 1;
            if (num_lives == 0) {
                loseGame();
            }
            livesView.setText("Lives: " + num_lives);
        }
        else {
            img_button.setImageResource(R.drawable.rose);
            roses_clicked += 1;
            if (roses_clicked == max_roses) {
                winGame();
            }
        }
    }

    private void loseGame() {
        textView.setText("YOU LOSE ! ");
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setEnabled(false);
        }
    }

    private void winGame() {
        textView.setText("YOU WIN !! ");
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setEnabled(false);
        }
    }

}
