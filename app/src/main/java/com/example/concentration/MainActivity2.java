package com.example.concentration;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity2 extends ActionBarActivity {

    ImageView[] images = new ImageView[16];
    Integer[] answers = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
    int answer1,answer2,card1,card2 ,f,i,scorep1,scorep2;
    TextView text2,text3,text4;
    boolean imgopen,player,wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity2_main);

        LinearLayout background = (LinearLayout)findViewById(R.id.LinearLayout2);
        background.setBackgroundResource(R.drawable.bg1);

        text2 = (TextView)findViewById(R.id.textView6);
        text2.setText("player1");
        text3 = (TextView)findViewById(R.id.textView8);
        text3.setText("0");
        text4 = (TextView)findViewById(R.id.textView10);
        text4.setText("0");

        imgopen = wait = false;
        player = true;  /*trueのときplayer1,falseのときplayer2*/

        scorep1 = scorep2 = 0;

        List<Integer> list = Arrays.asList(answers);
        Collections.shuffle(list);
        answers = (Integer[])list.toArray(new Integer[list.size()]);

        for(i=0;i<=15;i++){
            images[i] = (ImageView) findViewById(
                    getResources().getIdentifier("image" + i, "id", getPackageName()));
            images[i].setImageResource(R.drawable.cardback);
            images[i].setTag(i);
        }

        f = 0;
    }

    public void find(View v){
        int click_number = (Integer) v.getTag();

        if(!imgopen){
            imgset(click_number,answers[click_number]);
            imgopen = true;
            card1 = click_number;
            answer1 = answers[click_number];
        }else{
            imgset(click_number,answers[click_number]);

            card2 = click_number;
            answer2 = answers[click_number];
            if(answer1 == answer2){
                score(player);
                checkallopen();
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgset(card1,8);
                        imgset(card2,8);
                        playerchange();
                        who(player);
                    }
                },500);
            }
            imgopen = false;

        }

    }


    public void imgset(int a,int b){
        switch (b){
            case 0:
                images[a].setImageResource(R.drawable.circle);
                break;
            case 1:
                images[a].setImageResource(R.drawable.clover);
                break;
            case 2:
                images[a].setImageResource(R.drawable.diamond);
                break;
            case 3:
                images[a].setImageResource(R.drawable.heart);
                break;
            case 4:
                images[a].setImageResource(R.drawable.pentagon);
                break;
            case 5:
                images[a].setImageResource(R.drawable.spade);
                break;
            case 6:
                images[a].setImageResource(R.drawable.star);
                break;
            case 7:
                images[a].setImageResource(R.drawable.triangle);
                break;
            default:
                images[a].setImageResource(R.drawable.cardback);
        }

    }

    public void reset(View v){
        new Handler().post (new Runnable(){
            public void run() {
                Intent intent = new Intent(MainActivity2.this,MainActivity2.class);
                startActivity(intent);
            }
        });

    }


    public void checkallopen(){
        f++;
        if(f == 8)
            if(scorep1 == scorep2) {
                LinearLayout background = (LinearLayout) findViewById(R.id.LinearLayout2);
                background.setBackgroundResource(R.drawable.bg3);
                text2.setText("引き分け");
            }else if(scorep1 > scorep2){
                LinearLayout background = (LinearLayout) findViewById(R.id.LinearLayout2);
                background.setBackgroundResource(R.drawable.bg1);
                text2.setText("player1の勝ち");
            }else if(scorep1 < scorep2) {
                LinearLayout background = (LinearLayout) findViewById(R.id.LinearLayout2);
                background.setBackgroundResource(R.drawable.bg2);
                text2.setText("player2の勝ち");
            }
    }

    public void who(boolean player){
        if(player)
            text2.setText("player1");
        else
            text2.setText("player2");
    }

    public void playerchange(){
        if(player) {
            player = false;
            LinearLayout background = (LinearLayout) findViewById(R.id.LinearLayout2);
            background.setBackgroundResource(R.drawable.bg2);
        }else {
            player = true;
            LinearLayout background = (LinearLayout) findViewById(R.id.LinearLayout2);
            background.setBackgroundResource(R.drawable.bg1);
        }
    }

    public void score(boolean player){
        if(player) {
            scorep1++;
            text3.setText(String.valueOf(scorep1));

        }else {
            scorep2++;
            text4.setText(String.valueOf(scorep2));
        }
    }

    public void menu(View v){
        Intent intent = new Intent(MainActivity2.this,StartMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
