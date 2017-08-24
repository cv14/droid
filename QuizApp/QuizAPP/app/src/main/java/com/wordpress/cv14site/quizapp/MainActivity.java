package com.wordpress.cv14site.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addScore(View v){

        Button button = (Button) v;
        Button button2 = (Button) findViewById(R.id.wrongAns1);
        Button button3 = (Button) findViewById(R.id.wrongAns2);
        Button button4 = (Button) findViewById(R.id.wrongAns3);

        if(button.getId() == R.id.rightAns) {
            score = score + 1;

            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }else{
            button = (Button) findViewById(R.id.rightAns);
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }
    }

    public void addScoreRB(View v){
        RadioButton rb = (RadioButton) v;
        if(rb.getId() == R.id.radio_pirates1){
            score = score + 1;
        }else{
        }
    }

    public void submitMeth(View v){
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGG);

        for (int i = 0; i < rg.getChildCount(); i++) {
            rg.getChildAt(i).setEnabled(false);
        }

        EditText et = (EditText) findViewById(R.id.stadium);
        String message = et.getText().toString();
        et.setEnabled(false);

        if(message.equals("Old Trafford")){
            score = score + 1;
        }

        int[] anArray = {R.id.zlatan,R.id.rooney,R.id.ronaldo, R.id.lukaku};
        for(int i = 0; i < 4; i++){
            CheckBox cc = (CheckBox) findViewById(anArray[i]);
            cc.setEnabled(false);
        }


        displayForTeamA(score);
        Toast.makeText(getApplicationContext(), "Your Total Score is : " + score,
                Toast.LENGTH_LONG).show();

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.zlatan:
                if (checked)
                    score += 1;
                break;
            case R.id.rooney:
                if (checked)
                    score -= 1;
                break;
            case R.id.ronaldo:
                if(checked)
                    score -= 1;
                break;
            case R.id.lukaku:
                if (checked)
                    score += 1;
                break;
        }
    }

    public void displayForTeamA(int score) {
        TextView sV = (TextView) findViewById(R.id.scoreView);
        String ss = "Your Total Score is : " + score;
        sV.setText(String.valueOf(ss));
    }
}
