package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;

    //arraylist to hold answer
    ArrayList<Integer>answers=new ArrayList<Integer>();

    int locationOfCorrectAns;
    TextView result;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;

    //mcqs buttons
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;

    TextView sumTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    public void playAgain(View view)
    {
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+ "/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        result.setText("");

        //set the timer
        new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish()
            {
                result.setText("DONE!!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    public void chooseAnswer(View view)
    {
       if (Integer.toString(locationOfCorrectAns).equals(view.getTag().toString()))
       {
           result.setText("Correct!");
           score++;
       }else
       {
           result.setText("Wrong :(");
       }
      numberOfQuestions++;
      scoreTextView.setText(Integer.toString(score)+ "/"+Integer.toString(numberOfQuestions));
      newQuestion();
    }

    public void newQuestion()
    {
        Random rand=new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAns=rand.nextInt(4);

        answers.clear();
        //set the answers
        for(int i=0; i<4; i++)
        {
            if(i==locationOfCorrectAns)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer=rand.nextInt(41);
                //our random answer should not be equal to the right answers therefore generate another answer
                while(wrongAnswer == a+b)
                {
                    wrongAnswer=rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void start(View view)
    {
        //when pressed it becomes invisible
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.scoreTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.questionTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgain=findViewById(R.id.playAgainButton);

        gameLayout=findViewById(R.id.gameLayout);

        goButton=findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        result=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);






    }
}
