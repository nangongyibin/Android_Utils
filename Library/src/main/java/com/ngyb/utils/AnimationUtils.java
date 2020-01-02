package com.ngyb.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/2 16:10
 */
public class AnimationUtils {
    public static void animationViewHeight(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = height;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }

    public static void rotateView(View view,float startAngle,float endAngle){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", startAngle, endAngle);
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }
}
