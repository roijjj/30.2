package com.bignerdranch.android.sunset;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ArgbEvaluator;

import android.animation.PropertyValuesHolder;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    ObjectAnimator heatAnimator;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);
        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);

        dayAnimation();
        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
                heatAnimator.end();


            }
        });


        return view;
    }
    private void startAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunYStart, sunYEnd).setDuration(3000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor).setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor).setDuration(1500);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(sunsetSkyAnimator).before(nightSkyAnimator);
        animatorSet.start();

    }

    private void dayAnimation(){



/*
        ObjectAnimator auraAnimator = ObjectAnimator.ofPropertyValuesHolder(mSunView, PropertyValuesHolder.ofFloat("x",1.2f)
        ,PropertyValuesHolder.ofFloat("y",1.2f));

        auraAnimator.setDuration(600);
        auraAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        auraAnimator.setRepeatMode(ObjectAnimator.REVERSE);
*/

         heatAnimator = ObjectAnimator.ofPropertyValuesHolder(mSunView,
                PropertyValuesHolder.ofFloat("scaleX", 1.3f)
                , PropertyValuesHolder.ofFloat("scaleY", 1.3f))
                .setDuration(1000);
        heatAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        heatAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heatAnimator);
        animatorSet.start();


    }

}
