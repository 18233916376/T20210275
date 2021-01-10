package com.example.epidemicinfo.splash;

import androidx.appcompat.app.AppCompatActivity;
import com.example.epidemicinfo.R;
import com.example.epidemicinfo.chatRoom.ChatroomActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {


    private LinearLayout ll_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // 设置没有标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        ll_splash = findViewById(R.id.ll_splash);


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.9f, 1.0f);
        alphaAnimation.setDuration(2000);//设置动画播放时长1000毫秒（1秒）
        ll_splash.startAnimation(alphaAnimation);

        //设置动画监听
        alphaAnimation.setAnimationListener(new AnimationListener());


    }




    class AnimationListener implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ll_splash.setBackgroundResource(R.drawable.splash2);

            Handler mHandler = new Handler();


            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //页面的跳转
                    Intent intent = new Intent(SplashActivity.this, ChatroomActivity.class);
                    startActivity(intent);
                    finish();


                }
            }, 800);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }





}

