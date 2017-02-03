package com.example.jovan.brainwarmup;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    TextView question;
    TextView score;
    TextView correct;
    Button playAgain;
    Button startBtn;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    GridLayout gridLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int  positionOfCorrectAnswer;
    int scoore =0;
    int questionNumber = 0;

    public void start(View view){

        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        startBtn.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        scoore = 0;
        questionNumber = 0;
        score.setText("0/0");

        questionGenerator();

        timer.setText("30");

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {

                timer.setText(String.valueOf(l/1000) + "s");

            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
                question.setVisibility(View.INVISIBLE);
                score.setText("Your score: " + Integer.toString(scoore) + "/" + Integer.toString(questionNumber));
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);


            }
        }.start();




    }
   public void questionGenerator(){
        Random random = new Random();

        int a = random.nextInt(31);
        int b = random.nextInt(31);

        question.setText(Integer.toString(a) + "+" + Integer.toString(b));
        positionOfCorrectAnswer = random.nextInt(4);
        int  incorrectAnswer;

       answers.clear();

        for(int i = 0; i<4; i++){
            if(i == positionOfCorrectAnswer){

                answers.add(a+b);
            } else {
                incorrectAnswer = random.nextInt(61);
                while(incorrectAnswer == a+b) {
                    incorrectAnswer = random.nextInt(61);

                }
                answers.add(incorrectAnswer);

            }


        }
       button0.setText(Integer.toString(answers.get(0)));
       button1.setText(Integer.toString(answers.get(1)));
       button2.setText(Integer.toString(answers.get(2)));
       button3.setText(Integer.toString(answers.get(3)));


   }

    public void chooseAnswer(View view){
        correct.setVisibility(View.VISIBLE);

        if(view.getTag().toString().equals(Integer.toString(positionOfCorrectAnswer))){
            scoore++;
            correct.setText("Correct!");



        }else {

            correct.setText("Wrong!");

        }
        questionNumber++;
        score.setText(Integer.toString(scoore) + "/" + Integer.toString(questionNumber) );
        questionGenerator();



    }

    public void playAgain(View view){

        start(findViewById(R.id.button));
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.textView);
        question = (TextView)findViewById(R.id.textView2);
        score = (TextView)findViewById(R.id.textView3);
        correct = (TextView)findViewById(R.id.textView5);
        playAgain = (Button)findViewById(R.id.button6);
        gridLayout = (GridLayout)findViewById(R.id.grid);
        startBtn = (Button)findViewById(R.id.button);
        button0 = (Button)findViewById(R.id.button2);
        button1 = (Button)findViewById(R.id.button3);
        button2 = (Button)findViewById(R.id.button4);
        button3 = (Button)findViewById(R.id.button5);

    }
}
