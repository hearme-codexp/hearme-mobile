package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import br.senai.sp.informatica.mobile.apphearme.R;

/**
 * Created by CodeXP on 15/03/2018.
 */

public class Splash extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.main_layout);
        if (l != null) {
            l.clearAnimation();
            l.startAnimation(anim);
        }


        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        int SPLASH_DISPLAY_LENGTH = 3500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        /* Create an Intent that will start the Menu-Activity. */
                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}