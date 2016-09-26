package com.cab.alington.cab.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cab.alington.cab.R;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

public class LoginActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, View.OnClickListener {
    SliderLayout mSlider;
    PagerIndicator customIndicator;
    Button dr, cus;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSlider = (SliderLayout) findViewById(R.id.slider);
        customIndicator = (PagerIndicator) findViewById(R.id.custom_indicator);
        dr = (Button) findViewById(R.id.driver);
        cus = (Button) findViewById(R.id.user);
        dr.setOnClickListener(this);
        cus.setOnClickListener(this);
        sharedPreferences= this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        for (int i = 0; i < 5; i++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(R.drawable.car4)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            mSlider.addSlider(defaultSliderView);
            mSlider.setCustomIndicator(customIndicator);
           // dr.setCompoundDrawables(null,null,getResources().getDrawable(R.id.));
            //}
        }

    }

    @Override
        protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSlider.stopAutoCycle();
        super.onStop();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    SharedPreferences sharedPreferences;

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.driver) {
            editor.putBoolean("driver", true);
            editor.commit();
            dr.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.checkbox_on_background, 0, 0, 0);
        } else if (v.getId() == R.id.user) {
            editor.putBoolean("user", true);
            editor.commit();
           cus.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.checkbox_on_background, 0, 0, 0);
        }
        Intent i=new Intent(this,Login.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.right_in,
                R.anim.left_out);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editor.putBoolean("driver",false);
        editor.putBoolean("user",false);
        editor.commit();
        dr.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.checkbox_off_background, 0, 0, 0);
        cus.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.checkbox_off_background, 0, 0, 0);
    }
}
