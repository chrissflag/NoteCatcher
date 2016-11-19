package edu.brandeis.notecatcher;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import java.util.ArrayList;
import java.io.IOException;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.io.File;
public class Activity2 extends AppCompatActivity {

    private Button back;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_menu);
        ArrayList<String> FilesInFolder = GetFiles("/sdcard/noteCatcher");
        lv = (ListView)findViewById(R.id.filelist);

        lv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, FilesInFolder));

        back =(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain(v);
            }
        });

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                // Clicking on items
//            }
//        });
        
    }

    private ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<>();
        File f = new File(DirectoryPath);
        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++)
                MyFiles.add(files[i].getName());
        }

        return MyFiles;
    }

    public void backToMain(View view){
        Intent i = new Intent(Activity2.this, MainActivity.class);
        startActivity(i);
    }


}
