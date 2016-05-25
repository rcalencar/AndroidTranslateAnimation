package com.rodrigoalencar.animationexampleview;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mPlaceholder;
    private TextView mTitle;
    private TextView mSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlaceholder = (TextView) findViewById(R.id.animation_title_placeholder);
        mTitle = (TextView) findViewById(R.id.text_view_title);
        mSubtitle = (TextView) findViewById(R.id.text_view_subtitle);

        mTitle.setText(getString(R.string.title));
        mSubtitle.setText(getString(R.string.subtitle));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            Rect destViewRect = new Rect();
            mPlaceholder.getGlobalVisibleRect(destViewRect);
            Rect oriViewRect = new Rect();
            mTitle.getGlobalVisibleRect(oriViewRect);

            float distance = destViewRect.exactCenterY() - oriViewRect.exactCenterY();
            TranslateAnimation trans = new TranslateAnimation(
                    TranslateAnimation.ABSOLUTE, 0,
                    TranslateAnimation.ABSOLUTE, 0,
                    TranslateAnimation.ABSOLUTE, 0,
                    TranslateAnimation.ABSOLUTE, distance);
            trans.setInterpolator(this, android.R.anim.accelerate_interpolator);
            trans.setDuration(400);
            trans.setFillAfter(true);
            trans.setStartOffset(2000);
            mTitle.startAnimation(trans);

            trans.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mSubtitle.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}
