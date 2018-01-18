package com.examle.jaime.reversecrono;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by usuario on 18/01/18.
 */

@SuppressLint("AppCompatCustomView")
public class ReverseChronometer extends TextView implements Runnable {
    private long mOverallDuration;
    private long mWarningDuration;
    private long mStartTime;


    public ReverseChronometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attribute = getContext().obtainStyledAttributes(attrs, R.styleable.ReverseChronometer);
        mOverallDuration = attribute.getInteger(R.styleable.ReverseChronometer_overallduration, 60);
        mWarningDuration = attribute.getInteger(R.styleable.ReverseChronometer_warningduration, 10);
        reset();
    }


    @Override
    public void run() {
        long elapsedSeconds = (SystemClock.elapsedRealtime() - mStartTime) / 1000;

        if (elapsedSeconds < mOverallDuration) {
            //Actualizar los tiempos
            long remainingSeconds = mOverallDuration - elapsedSeconds;
            long minutes = remainingSeconds / 60;
            long seconds = remainingSeconds % 60;
            setText(String.format("%d:%02d", minutes, seconds));

            //En el caso que nos encontremos en tiempo de warning
            if (mWarningDuration > 0 && remainingSeconds < mWarningDuration)
                setTextColor(Color.RED);

            //Para un segundo y vuelve a llamar al método run()
            postDelayed(this, 1000);


        } else {
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }


    public void setOverallDuration(long overallDuration) {
        mOverallDuration = overallDuration;
    }


    public void setWarningDuration(long warningDuration) {
        mWarningDuration = warningDuration;
    }


    public long getOverallDuration() {
        return mOverallDuration;
    }


    public long getWarningDuration() {
        return mWarningDuration;
    }


    public void reset() {
        mStartTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
    }


    public void stop() {

    }
}
