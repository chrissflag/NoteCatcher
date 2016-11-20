package edu.brandeis.notecatcher;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;



public class MainActivity extends AppCompatActivity {

    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String outputFile = null;
    private ImageButton record;
    private Button finish;
    private Button play;
    private Button stop;
    private Button saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat sdf = new SimpleDateFormat("dd."+"MM."+"yyyy,"+"hh-"+"mm-"+"ss");
        String ts = sdf.format(new Date());

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/noteCatcher/"+ts+".3gpp";
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setOutputFile(outputFile);

        record =(ImageButton) findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            int i=1;
            @Override
            public void onClick(View v) {
                if(i % 2 ==0){
                    record.setBackgroundResource(android.R.drawable.presence_audio_online);
                    stopRecord(v);
                    savedMenu(v);
                }else{
                    startRecord(v);
                    record.setBackgroundResource(android.R.drawable.presence_audio_busy);
                }
                i++;

            }
        });

        /*finish =(Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecord(v);
            }
        });*/

        /*play =(Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecord(v);
            }
        });

        stop =(Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay(v);
            }
        });*/

        /*saved =(Button)findViewById(R.id.saved);
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedMenu(v);
            }
        });*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(this, ExpenseAdd.class),request_Code);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void startRecord (View v) {
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //record.setEnabled(false);
        //finish.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Recording..",
                Toast.LENGTH_SHORT).show();
    }

    public void stopRecord(View v){
        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder  = null;

            //finish.setEnabled(false);
            record.setEnabled(false);
            play.setEnabled(true);

            Toast.makeText(getApplicationContext(), "Finishing..",
                    Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /*public void playRecord(View view) {
        try{
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(outputFile);
            mPlayer.prepare();
            mPlayer.start();
            play.setEnabled(false);
            stop.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Playing..",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    /*public void stopPlay(View view) {
        try {
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
                play.setEnabled(true);
                stop.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Stoping..",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void savedMenu(View view){
        Intent i = new Intent(MainActivity.this, Activity2.class);
        startActivity(i);

    }


}
