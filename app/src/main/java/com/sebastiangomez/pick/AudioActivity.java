package com.sebastiangomez.pick;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.IOException;


public class AudioActivity extends Activity {

    private MediaPlayer mediaplayer;
    private MediaRecorder recorder;
    private String OUTPUT_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        OUTPUT_FILE = Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
    }

    public void buttonTapped(View view){

        switch(view.getId()){

            case R.id.bGrabar:
                try{
                    beginRecording();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.bDetener:
                try{
                    stopRecording();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.bReproducir:
                try{
                    playRecording();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

        }
    }

    private void beginRecording() throws IOException {
        ditchMediaRecorder();
        File outFile = new File(OUTPUT_FILE);

        if(outFile.exists())
            outFile.delete();

        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE);
        recorder.prepare();
        recorder.start();
    }

    private void stopRecording() {
        if(recorder!=null)
            recorder.stop();
    }

    private void playRecording() throws IOException {
        ditchMediaPlayer();
        mediaplayer=new MediaPlayer();
        mediaplayer.setDataSource(OUTPUT_FILE);
        mediaplayer.prepare();
        mediaplayer.start();
    }

    private void ditchMediaPlayer() {
        if(mediaplayer!=null)
            try{
                mediaplayer.release();
            }catch(Exception e){
                e.printStackTrace();
            }

    }

    private void ditchMediaRecorder() {
        if(recorder!=null)
            recorder.release();
    }


}
