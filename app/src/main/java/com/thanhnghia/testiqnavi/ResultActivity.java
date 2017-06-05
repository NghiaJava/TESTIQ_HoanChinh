package com.thanhnghia.testiqnavi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResult;
    private Button btnPlayAgain;
    private Button btnResultShare;
    private Button btnHome;
    private int score;
    
    private ListView listViewHistory;

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
        // list history
        String[] listHistory = getIntent().getStringArrayExtra("strListHistory");
        // hien thi score
        txtResult.setText("Your IQ is: " + (72+score*5) + ". \n THANK YOU !!!");
        // hien thi history
        listViewHistory = (ListView) findViewById (R.id.listViewHistory);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , listHistory);
        listViewHistory.setAdapter(arrayAdapter);
        
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
                    //.setContentTitle("Title")
                    .setQuote("Wow - Your IQ is: " + (score*5 + 72))
                    .setContentUrl(Uri.parse("www.thanhnghia.com"))
                    //.setRef("#testIQ")
                    .build();

            shareDialog.show(linkContent);
        }

    }
}
