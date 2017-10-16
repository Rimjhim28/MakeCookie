package com.example.android.makecookies;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtQues,txtPoints,txtAnalysis;
    RadioButton option1,option2;
    int counter = 0,points = 0;
    LinearLayout imageLayout;
    ImageView imageCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQues = (TextView) findViewById(R.id.txt_ques);
        txtPoints = (TextView) findViewById(R.id.points);
        txtAnalysis = (TextView) findViewById(R.id.txtAnalysis);

        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);

        txtQues.setText(Utils.game[counter][0]);
        imageLayout = (LinearLayout) findViewById(R.id.image_layout);
        imageCookie = (ImageView) findViewById(R.id.cookie);
    }

    public void answerChosen(View view){
        if(view == findViewById(R.id.option1)){
            if(Utils.game[counter][1] == "1")
                answerCorrect();
            else
                answerWrong();
        }
        else if(view == findViewById(R.id.option2)){
            if(Utils.game[counter][1] == "2")
                answerCorrect();
            else
                answerWrong();
        }
    }

    private void answerCorrect() {
        showImage();
        txtAnalysis.setText("You fetched an item");
        points++;
        counter++;
        txtPoints.setText(Integer.toString(points));
        nextQuestion();
    }

    private void answerWrong() {
        txtAnalysis.setText("You did not fetch anything");
        counter++;
        nextQuestion();
    }

    private void nextQuestion() {
        option1.setChecked(false);
        option2.setChecked(false);
        if((counter == Utils.game.length) && (points == 6)){
            Toast.makeText(this,"You made a cookie",Toast.LENGTH_SHORT).show();
            imageLayout.setVisibility(View.GONE);
            imageCookie.setVisibility(View.VISIBLE);
        }
        else if ((counter == Utils.game.length) && (points != 6)){
            Toast.makeText(this,"You cannot make a cookie",Toast.LENGTH_SHORT).show();
        }
        else{
            final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            final AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
            fadeOut.setFillAfter(true);
            fadeIn.setDuration(400);
            fadeOut.setDuration(400);
            fadeIn.setFillAfter(true);
            fadeIn.setStartOffset(200);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    txtQues.setText(Utils.game[counter][0]);
                    txtQues.startAnimation(fadeIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            txtQues.startAnimation(fadeOut);
        }
    }
     public void showImage(){
        final AlphaAnimation fadeIn = new AlphaAnimation(0.2f,1.0f);
        fadeIn.setDuration(400);
        fadeIn.setFillAfter(true);
        fadeIn.setStartOffset(220);
        findViewById(Utils.image[points]).startAnimation(fadeIn);
        findViewById(Utils.image[points]).setAlpha(1.0f);
    }
}
