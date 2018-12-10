package com.example.admin.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView pointsTextView;
    TextView resultTextView;
    Button playagain;
    RelativeLayout gameRelativeLayout;
    int locationofcorrectanswer;
    TextView timerTextView;
    ArrayList <Integer> answers;
    TextView sumTextView;
    int correct=0,totalquestions=0;
    public void playAgain(View view){
        playAgainfunction();
    }
    public void playAgainfunction(){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        correct=0;
        totalquestions=0;
        pointsTextView.setText("0/0");
        timerTextView.setText("30s");
        resultTextView.setText("");
        playagain.setVisibility(View.INVISIBLE);

        generate();
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: "+Integer.toString(correct)+"/"+Integer.toString(totalquestions));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }


        }.start();

    }
    public void generate(){
        Random rand= new Random();
        int a =rand.nextInt(21);
        int b=rand.nextInt(21);
        int sum=a+b;
        int incorrectanswer;
        locationofcorrectanswer=rand.nextInt(4);
        answers.clear();
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        for(int i=0;i<4;i++){
            if(i==locationofcorrectanswer){
                answers.add(sum);
            }
            else{
                incorrectanswer=rand.nextInt(41);
                while(incorrectanswer==sum){
                    incorrectanswer=rand.nextInt(41);
                }
                answers.add(incorrectanswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void checkanswer(View view){
        

        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))){
            resultTextView.setText("CORRECT!");
            correct++;
        }
        else{
            resultTextView.setText("WRONG!");
        }
        totalquestions++;
        pointsTextView.setText(Integer.toString(correct)+"/"+Integer.toString(totalquestions));
        generate();
    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgainfunction();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startbutton);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playagain=(Button)findViewById(R.id.playagain);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        resultTextView=(TextView) findViewById(R.id.resultTextView);
        pointsTextView=(TextView) findViewById(R.id.pointsTextView);
        answers=new ArrayList<Integer>();

    }
}
