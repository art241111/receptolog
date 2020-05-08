package ru.art241111.cooltimer.utils;

import android.widget.TextView;

public class SetTime {
    public static void setTime(int time, TextView tv_time){
        int minutes = time/60;
        int seconds = time - (minutes*60);

        tv_time.setText(doTwoNumberInTime(minutes) + ":" + doTwoNumberInTime(seconds));
    }
    private static String doTwoNumberInTime(int time){
        if(time < 10) return "0" + time;
        else return String.valueOf(time);
    }
}
