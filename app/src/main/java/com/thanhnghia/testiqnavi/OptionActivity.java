package com.thanhnghia.testiqnavi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    SeekBar sb;
    //MediaPlayer mp;
    AudioManager am;
    int Volume=0;

    Button btnOptionHome;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btnOptionHome = (Button) findViewById (R.id.btnOptionHome);

        sb  =(SeekBar) findViewById(R.id.seekBarMusic);
        //mp = MediaPlayer.create(this, R.raw.music);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //mp.start();
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        sb.setMax(maxVolume);
        sb.setProgress(curVolume);

        sb.setOnSeekBarChangeListener(this);
        btnOptionHome.setOnClickListener (this);
    }

    public void onProgressChanged(SeekBar seekb, int progress, boolean arg2) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        Volume = progress;
    }

    public void onStartTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub

    }

    public void onStopTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub
        // Toast.makeText(getApplicationContext(), "Volume: " + Integer.toString(Volume), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOptionHome:
                backOptionHome(v);
                break;
        }
    }

    public void backOptionHome(View o){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
