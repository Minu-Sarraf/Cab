package com.cab.alington.cab.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.cab.alington.cab.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class Login extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    EditText etphone, etpw, etmail;
    AppCompatTextView taxicab;
    Button btlogin;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        etphone = (EditText) findViewById(R.id.phone);
        etmail = (EditText) findViewById(R.id.user);
        etpw = (EditText) findViewById(R.id.password);
        taxicab = (AppCompatTextView) findViewById(R.id.taxi);
        btlogin = (Button) findViewById(R.id.login);
        etpw.setVisibility(View.GONE);
        etphone.setVisibility(View.GONE);
        etmail.setVisibility(View.GONE);
        btlogin.setVisibility(View.GONE);
        btlogin.setOnClickListener(this);


    }
    public void animate(final View id, final Techniques anim, long delay) {
        YoYo.with(anim).delay(0)
                .duration(delay).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (id == etphone) {

                }
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                animatelogin(etmail, Techniques.SlideInLeft, 600);
                animatelogin(etpw, Techniques.SlideInLeft, 800);
                animatelogin(etphone, Techniques.SlideInLeft, 1000);
                animatelogin(btlogin, Techniques.Landing, 1000);
                flag = 1;

                //
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

    private void animatelogin(final View id, Techniques anim, int delay) {
        YoYo.with(anim).delay(0)
                .duration(delay).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (id != etphone && id != taxicab) {
                    id.setVisibility(View.VISIBLE);
                } else if (id != taxicab) {
                    if (sharedPreferences.getBoolean("driver", false)) {
                        Log.e("app", "app");
                        id.setVisibility(View.GONE);
                    } else if (sharedPreferences.getBoolean("user", false)) {
                        id.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {


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


    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        flag = 1;
        Log.e("flag","complete");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(flag==0) {
            animate(taxicab, Techniques.SlideInDown, 600);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
