package com.chan.slidingofflayout;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

/**
 * Created by chan on 15-7-16.
 */
public class KuGouLayout
        extends LinearLayout
    implements View.OnTouchListener{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static final float s_degreeThreshold = 30f;
    private static final short s_duration = 1000;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //屏幕寬高
    private int m_width;
    private int m_height;
    private Context m_context;
    private OnCloseListener m_closeListener;
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public KuGouLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_context = context;

        init();
    }

    @SuppressWarnings("deprecated")
    private void init(){

        //获得窗口的大小，我们默认的旋转点就是右下角的
        WindowManager manager = (WindowManager)
                m_context.getSystemService(
                        Context.WINDOW_SERVICE
                );
        Display display = manager.getDefaultDisplay();

        m_width = display.getWidth();
        m_height = display.getHeight();

        //设置旋转点
        this.setPivotX(m_width);
        this.setPivotY(m_height);

        //设置监听器
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float w = getPivotX() - event.getRawX();
                float h = getPivotY() - event.getRawY();

                float degree = (float) Math.toDegrees(Math.atan(h / w)) / 2;
                KuGouLayout.this.setRotation(degree);
                break;
            case MotionEvent.ACTION_UP:

                float rotation = KuGouLayout.this.getRotation();

                //如果旋转角度超过了阈值 那么就调用关闭接口
                if(rotation >= s_degreeThreshold && m_closeListener != null){
                    m_closeListener.onCloseListener();
                }else{
                    //否则只是简单的晃动窗口
                    shake(rotation);
                }

                break;
            default: break;
        }
        return true;
    }

    private void shake(float rotation) {

        //整个动画执行平滑的变化
        ValueAnimator animator = ValueAnimator.ofObject(new FloatEvaluator(), rotation, 0f);
        animator.setDuration(s_duration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Number number = (Number) animation.getAnimatedValue();
                KuGouLayout.this.setRotation(number.floatValue());
            }
        });

        //使用物理补间器 使得关闭效果更加真实
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    public interface OnCloseListener{
        public void onCloseListener();
    }

    public void setOnCloseListener(OnCloseListener listener){ m_closeListener = listener; }
    public OnCloseListener getOnCloseListener() { return m_closeListener; }
}
