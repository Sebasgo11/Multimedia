package com.sebastiangomez.pick;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends Activity{

    private EditText editText;
    private Button ButtonExaminar;
    private final int PICKER=1;
    private ImageButton play;
    private VideoView Reproductor;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonExaminar=(Button)findViewById(R.id.button_Examinar);
        editText = (EditText) findViewById(R.id.path);
        play=(ImageButton)findViewById(R.id.play);
        Reproductor=(VideoView)findViewById(R.id.surfaceView);

        ButtonExaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickFile();
            }
        });

        Reproductor.setMediaController(new MediaController(this));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = editText.getText().toString();
                Reproductor.setVideoPath(path);
                Reproductor.start();
                Reproductor.requestFocus();
            }
        });

    }

    private void PickFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try{
            startActivityForResult(
                    Intent.createChooser(intent,"Seleccione Archivo Para Reproducir"),PICKER);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(this, "Por Favor Instale Un Administrador De Archivos", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICKER:{
                if(resultCode==RESULT_OK){
                    String FilePath=data.getData().getPath();
                    editText.setText(FilePath);
                }
                break;
            }
        }
    }

}
