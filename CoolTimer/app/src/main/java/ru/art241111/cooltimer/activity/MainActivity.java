package ru.art241111.cooltimer.activity;

import androidx.appcompat.app.AppCompatActivity;
import ru.art241111.cooltimer.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static ru.art241111.cooltimer.utils.SetTime.setTime;

public class MainActivity extends AppCompatActivity
        implements  SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView tv_time;
    private  SeekBar seekBar;
    CountDownTimer myTimer;
    Button bt_start;
    boolean isTimerStart = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_start = findViewById(R.id.bt_start);
        tv_time = findViewById(R.id.tv_timer);



        customizationSeekBar();
        setChangeListenerOnSeekBar();

        setClickListenerOnButton(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setIntervalFromSharedPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
     }

    private void setClickListenerOnButton(final Context context) {
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerStart){
                    startTimer(seekBar.getProgress(), context);

                } else{
                    myTimer.cancel();
                    endTimer();
                }
            }
        });
    }

    private void customizationSeekBar() {
        seekBar = findViewById(R.id.sb_time);
        seekBar.setMax(600);
    }

    private void setChangeListenerOnSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTime(progress,tv_time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void startTimer(final int progress, final Context context) {

            myTimer = new CountDownTimer(progress * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setTime((int) (millisUntilFinished / 1000), tv_time);
            }

            @Override
            public void onFinish() {
                SharedPreferences sharedPreferencesMusic
                        = sharedPreferences;

                if(sharedPreferencesMusic.getBoolean("enable_sound", true)){
                    String melodyName
                            = sharedPreferencesMusic.getString("timer_melody", "bell");
                    MediaPlayer mediaPlayer;
                    if(melodyName.equals("bell")){
                        mediaPlayer = MediaPlayer.create(context, R.raw.bell_call);
                    } else if(melodyName.equals("bip")){
                        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_siren);
                    } else {
                        mediaPlayer = MediaPlayer.create(context, R.raw.bip);
                    }

                    mediaPlayer.start();
                }
                endTimer();
            }
        };
        myTimer.start();

        bt_start.setText("STOP");
        seekBar.setEnabled(false);
        isTimerStart = true;
    }

    private void endTimer() {
        setTime(seekBar.getProgress(),tv_time);

        bt_start.setText("Start");
        seekBar.setEnabled(true);

        setIntervalFromSharedPreferences(sharedPreferences);

        isTimerStart = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.timer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case(R.id.action_settings):
                Intent openSettings = new Intent(this, SettingsActivity.class);
                startActivity(openSettings);
                return true;
            case(R.id.action_about):
                Intent openAbout = new Intent(this, AboutActivity.class);
                startActivity(openAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setIntervalFromSharedPreferences(SharedPreferences sharedPreferences) {

        int defaultInterval = Integer.parseInt(sharedPreferences.getString("default_interval","30"));



        setTime(defaultInterval,tv_time);
        seekBar.setProgress(defaultInterval);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("default_interval")){
         setIntervalFromSharedPreferences(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

}
