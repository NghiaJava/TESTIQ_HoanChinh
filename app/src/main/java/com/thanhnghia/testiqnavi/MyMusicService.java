package com.thanhnghia.testiqnavi;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class MyMusicService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone
        player = MediaPlayer.create(this, R.raw.music);
        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);

        //staring the player
        player.start();

        //we have some options for service
        //start sticky means service will be explicity started and stopped

        stopForeground(true);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player != null)
        {
            try{
                player.stop();
                player.release();
            }finally {
                player = null;
            }
        }
    }
}
