package com.sebastiangomez.pick;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActivityMenu extends Activity {

    private Button VIDEO;
    private Button AUDIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu);

        VIDEO = (Button) findViewById(R.id.video);
        AUDIO = (Button) findViewById(R.id.audio);

    }


    public void newVideo(View view){
         Intent A1 = new Intent(this,MainActivity.class);
         startActivity(A1);
}

    public void newAudio(View view){
        Intent A2 = new Intent(this,AudioActivity.class);
        startActivity(A2);
    }

}
