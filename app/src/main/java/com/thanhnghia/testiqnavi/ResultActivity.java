package com.thanhnghia.testiqnavi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResult;
    private Button btnPlayAgain;
    private Button btnResultShare;
    private Button btnHome;
    private int score;

    // share FB

    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // gan id
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnResultShare = (Button) findViewById (R.id.btnResultShare);
        btnHome = (Button) findViewById(R.id.btnResultHome);

        // set click
        btnPlayAgain.setOnClickListener(this);
        btnResultShare.setOnClickListener (this);
        btnHome.setOnClickListener(this);

        // bundle chuyen du lieu score
        Bundle b = getIntent().getExtras();
        // diem so
        score = b.getInt("score");
        // hien thi
        txtResult.setText("Your IQ is: " + (72+score*5) + ". \n THANK YOU !!!");

        // share FB

        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlayAgain:
                playAgain(v);
                break;

            case R.id.btnResultHome:
                backHome(v);
                break;

            case R.id.btnResultShare:
                shareTutorialonFB(v);
                break;
        }
    }

    public void playAgain(View o){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void backHome(View o){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void postStatusUpdate() {

    }

    // When Share this tutorial on FB button is clicked
    public void shareTutorialonFB(View v){
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Wow - Your IQ is: " + (72+score*5))
                    .setImageUrl(Uri.parse("http://www.kinesissurvey.com/wp-content/uploads/2013/11/IQ.png"))
                    .setContentDescription(
                            "Developer By TTN")
                    .setContentUrl(Uri.parse("www.thanhnghia.com"))
                    .build();

            shareDialog.show(linkContent);  // Show facebook ShareDialog
        }
    }
}
