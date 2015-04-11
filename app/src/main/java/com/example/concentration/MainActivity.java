package com.example.concentration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import android.os.Handler;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {
	
	ImageView[] images = new ImageView[16];
    Integer[] answers = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
	int answer1,answer2,card1,card2 ,f,i,scorep1;
    TextView text,text3;
    boolean imgopen,player,wait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.textView);
        text.setText("");

        text3 = (TextView)findViewById(R.id.textView3);
        text3.setText("8");

        imgopen = wait = false;
        player = true;  /*trueのときplayer1,falseのときplayer2*/

        scorep1 = 8;

        List<Integer> list = Arrays.asList(answers);
        Collections.shuffle(list);
        answers = (Integer[])list.toArray(new Integer[list.size()]);

        for(i=0;i<=15;i++){
        	images[i] = (ImageView) findViewById(
        			getResources().getIdentifier("ImageView" + i, "id", getPackageName()));
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
                text.setText("あたり");
                checkallopen();
                score(player);
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("はずれ");
                        imgset(card1,8);
                        imgset(card2,8);
                    }
                },500);

                playerchange();
            }
            imgopen = false;

        }
//        who(player);
    }


    public void imgset(int a,int b){
        switch (b){
            case 0:
//                Animation animation = AnimationUtils.loadAnimation(this,R.anim.motion);
//                images[a].startAnimation(animation);
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

    public void reset(View v) {
        new Handler().post(new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void checkallopen(){
        f++;
        if(f == 8)
            text.setText("クリア");

    }

//    public void who(boolean player){
//        if(player)
//            text2.setText("player1");
//        else
//            text2.setText("player2");
//    }

    public void playerchange(){
        if(player)
            player = false;
        else
            player = true;
    }

    public void score(boolean player){
            scorep1--;
            text3.setText(String.valueOf(scorep1));
    }

    public void menu(View v){
        Intent intent = new Intent(MainActivity.this,StartMenu.class);
        startActivity(intent);
    }

    public synchronized void sleep(long msec){
        {
            try {
                wait(msec);
            }catch (InterruptedException e){}
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
