package com.thanhnghia.testiqnavi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    // khai bao bien
    private Button startBtn;
    private Button exitBtn;
    private Button optionBtn;
    private Button shareBtn;

    private Intent musicService;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // gan ID
        startBtn = (Button) findViewById(R.id.btnPlay);
        exitBtn = (Button) findViewById(R.id.btnExit);
        optionBtn = (Button) findViewById(R.id.btnOption);
        shareBtn = (Button) findViewById(R.id.btnShare);

        // add listener
        startBtn.setOnClickListener(this);
        optionBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);

        // intent
        musicService = new Intent(MenuActivity.this, MyMusicService.class);

        startService (musicService);
        //Toast.makeText(MenuActivity.this, "start Service", Toast.LENGTH_LONG).show();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.thanhnghia.testiqnavi",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        // share
        shareDialog = new ShareDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                playGame();
                break;

            case R.id.btnOption:
                viewOption();
                break;

            case R.id.btnShare:
                viewShare();
                break;

            case  R.id.btnExit:
                exitGame();
                break;
        }
    }

    private void playGame(){
        Intent intent = new Intent(this, PlayActivity.class);
        this.startActivity(intent);
    }

    private void  viewOption(){
        Intent option = new Intent(this, OptionActivity.class);
        this.startActivity(option);
    }

    private void viewShare(){
        //Intent share = new Intent(this, ShareActivity.class);
        //this.startActivity(share);

            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        //.setContentTitle("Title")
                        .setQuote("TEST IQ - NGHIA")
                        .setContentUrl(Uri.parse("www.thanhnghia.com"))
                        //.setRef("#testIQ")
                        .build();

                shareDialog.show(linkContent);
            }
            /*
        // Create an object
        ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                .putString("og:type", "TESTIQS.TESTIQ")
                .putString("og:title", "TEST IQ - TT.NGHIA")
                .build();

        // Create an action
        ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                .setActionType("TESTIQ.play")
                .putObject("TESTIQ", object)
                .build();

        // Create the content
        ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                .setPreviewPropertyName("TESTIQ")
                .setAction(action)
                .build();


        ShareDialog.show(MenuActivity.this, content); */
    }

    private void exitGame(){
        finish();
        stopService(musicService);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
