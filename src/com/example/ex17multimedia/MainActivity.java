package com.example.ex17multimedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	
	
	private MediaRecorder recorder;
	private MediaPlayer player;
	private ToggleButton record;
	private ToggleButton play;
	private static String fileName;
	private File file;
	
	private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
        	Toast.makeText(getApplicationContext(), "erro", Toast.LENGTH_SHORT).show();
        }
    }
	private void stopPlaying() {
        player.release();
        player = null;
    }
	
	
    
	
	private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
        	Toast.makeText(getApplicationContext(), "erro", Toast.LENGTH_SHORT).show();
        }

        recorder.start();
    }
	
	private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }
	
	public void AudioRecordTest() {
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/teste.3gp";
        file = new File(fileName);
    }
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 record=(ToggleButton) findViewById(R.id.botaoGravar);
		 play=(ToggleButton) findViewById(R.id.botaoPlay);
		
		
		record.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	AudioRecordTest();
		        	
		        	Toast.makeText(getApplicationContext(), fileName.toString(), Toast.LENGTH_SHORT).show();
		        	startRecording();
		        	
		        	
		        	
		        } else {
		        	stopRecording();
		        }
		    }
		});
		
		play.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	
		        	
		        	
		        	Toast.makeText(getApplicationContext(),"a reproduzir"+ fileName.toString(), Toast.LENGTH_SHORT).show();
		        	startPlaying();
		        	player.setOnCompletionListener(new OnCompletionListener() {

		                @Override
		                public void onCompletion(MediaPlayer player) {
		                    play.setChecked(false);
		                }

		                });
		        
		        	
		        	
		        	
		        	
		        } else {
		        	stopPlaying();
		        }
		    }
		});
	}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
