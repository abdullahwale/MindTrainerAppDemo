package com.example.premier.mindtrainerappdemo;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView QuestionTextView,scoreTextV,timerTextView;
    Button b1,b2,b3,b4;
    int correctAnswer;
    ArrayList<Integer> answerList;
    TextView resultTextView;
    int numberOfQuestions=0;
    int correctAttempts=0;
    Button btnPlayAgain,btnGo;
    ConstraintLayout constraintLayout;


    public void GameLayout(View view){
        constraintLayout.setVisibility(View.VISIBLE);
        btnGo.setVisibility(View.INVISIBLE);
    }

    public void  PlayAgainButton(View View)
    {
        btnPlayAgain.setVisibility(View.INVISIBLE);
        numberOfQuestions=0;
        correctAttempts=0;
        timerTextView.setText("0s");
        scoreTextV.setText("0 / 0");
        resultTextView.setText(" ");
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        QuetionTimer();
        QuestionGenrator();

    }
    public void QuestionGenrator()
    {
        numberOfQuestions++;
        Random random= new Random();

        int a,b;
        a=random.nextInt(10);
        b=random.nextInt(10);

        correctAnswer= a+b;
        QuestionTextView.setText(String.valueOf(a) + "+" + String.valueOf(b) );

        answerList=new ArrayList<Integer>();
        int locationOfCorrectAnswer = random.nextInt(4);
        for(int i = 0;i < 4; i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answerList.add(correctAnswer);
            }

            int incorrectAnswer = random.nextInt(20);

            while(correctAnswer == incorrectAnswer)
            {
                incorrectAnswer = random.nextInt(20);
            }
            answerList.add(incorrectAnswer);

        }

        b1.setText(String.valueOf(answerList.get(0)));
        b2.setText(String.valueOf(answerList.get(1)));
        b3.setText(String.valueOf(answerList.get(2)));
        b4.setText(String.valueOf(answerList.get(3)));
    }
    public void QuetionTimer()
    {
        new CountDownTimer(10200,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your Final Score is: " + String.valueOf(correctAnswer));
                b1.setClickable(false);
                b2.setClickable(false);
                b3.setClickable(false);
                b4.setClickable(false);

                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void answerButtonClicked(View view)
    {
        int buttonID=Integer.parseInt(view.getTag().toString());
        //Toast.makeText(this, String.valueOf(buttonID), Toast.LENGTH_SHORT).show();

        if(correctAnswer == answerList.get(buttonID)){
            correctAttempts++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Incorrect");
        }

        scoreTextV.setText(String.valueOf(correctAttempts) + " / " + String.valueOf(numberOfQuestions));
        QuestionGenrator();
     //   QuetionTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionTextView=findViewById(R.id.questionstatmentTextView);
        resultTextView=findViewById(R.id.rsltTV);
        scoreTextV=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        btnPlayAgain=findViewById(R.id.btnPlayAgain);
        constraintLayout=findViewById(R.id.GameLayout);
        btnGo=findViewById(R.id.btnGo);



        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);

        
    }
}
