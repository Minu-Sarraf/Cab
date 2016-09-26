package com.cab.alington.cab.splashscreen;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.cab.alington.cab.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class Register extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatTextView tvprice;
    CardView card;
    TextView tvadd, tvdate, tvpass;
    AppCompatEditText tvothers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar.setTitle("Payment");
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        tvprice = (AppCompatTextView) findViewById(R.id.price);
        card = (CardView) findViewById(R.id.cardprice);
        tvothers = (AppCompatEditText) findViewById(R.id.other);
        tvadd = (TextView) findViewById(R.id.address);
        tvdate = (TextView) findViewById(R.id.date);
        tvpass = (TextView) findViewById(R.id.passenger);
        tvothers.setVisibility(View.GONE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.leftin,
                        R.anim.right_out);
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animate(tvprice, Techniques.SlideInDown, 1200);
        animate(tvadd, Techniques.FadeInUp, 1000);
        animate(tvdate, Techniques.FadeInUp, 1200);
        animate(tvpass, Techniques.FadeInUp, 1400);
       // animate(tvothers, Techniques.Shake, 1700);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void animate(final View id, Techniques anim, long duration) {
        YoYo.with(anim)
                .duration(duration).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (id == tvprice) {
                    YoYo.with(Techniques.StandUp).duration(700).playOn(findViewById(R.id.price));

                } else if (id == tvpass){
                    tvothers.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Shake).duration(300).playOn(tvothers);

                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        })
                .playOn(id);
    }
}
