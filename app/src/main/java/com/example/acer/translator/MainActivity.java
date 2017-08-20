package com.example.acer.translator;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener




    private TextToSpeech engine;
    private ImageView speechButton;
    private EditText editText;
    private SeekBar seekPitch;
    private SeekBar seekSpeed;
    private double pitch =1.0;
    private double speed =1.0;

    @Override
    public void onInit(int status) {

        Log.d("Speech","OnInit - Status ["+status+"]");

        if(status == TextToSpeech.SUCCESS){

            Log.d("Speech","Success!");
            engine.setLanguage(Locale.UK);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference
        speechButton =(ImageView) findViewById(R.id.speechImg);
        editText = (EditText) findViewById(R.id.sentence);
        engine = new TextToSpeech(this,this);

        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech();
            }
        });

        seekPitch = (SeekBar) findViewById(R.id.seekPitch);
        seekPitch.setThumbOffset(5);
        seekPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d("Speech","Progress ["+progress+"]");
                pitch = (float) progress/(seekBar.getMax()/2);
                Log.d("Speech","Pitch ["+pitch+"]");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekSpeed = (SeekBar) findViewById(R.id.seekSpeed);
        seekSpeed.setThumbOffset(5);
        seekSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d("Speech","Progress ["+progress+"]");
                speed = (float) progress/(seekBar.getMax()/2);
                Log.d("Speech","Pitch ["+pitch+"]");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

        private void speech(){

            engine.setPitch((float) pitch);
            engine.setSpeechRate((float) speed);
            engine.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);

         }


}
