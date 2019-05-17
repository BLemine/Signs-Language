package com.example.root.SignsLanguage;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import android.widget.VideoView;

import java.util.ArrayList;

public class SpeechSigns extends AppCompatActivity implements RecognitionListener {
    Button clk;
    String chaine;
    //EditText chaineInput;
    VideoView videov;
    VideoView videov1;
    VideoView videov5;
    private static final int REQUEST_RECORD_PERMISSION = 100;
    private TextView returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_transc);
        returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);

        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    ActivityCompat.requestPermissions(SpeechSigns.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
                } else {
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    speech.startListening(recognizerIntent);
                } else {
                    Toast.makeText(SpeechSigns.this, "Permission Denied!", Toast
                            .LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() { super.onPause(); }

    @Override
    protected void onStop() {
        super.onStop();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }
    }


  //  @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }


   // @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

   // @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);
    }

    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);
        toggleButton.setChecked(false);
    }

   // @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

   // @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

   // @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }
    Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String videopath5 = "android.resource://com.example.salah.myapplication/" + R.raw.madame;
            Uri uri5 = Uri.parse(videopath5);
            videov.setVideoURI(uri5);
            videov.start();
        }
    };

    Handler handler2 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String videopath5 = "android.resource://com.example.salah.myapplication/" + R.raw.bonjour;
            Uri uri5 = Uri.parse(videopath5);
            videov.setVideoURI(uri5);
            videov.start();
        }
    };

    Handler handlerinv1 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String videopath5 = "android.resource://com.example.salah.myapplication/" + R.raw.madame;
            Uri uri5 = Uri.parse(videopath5);
            videov.setVideoURI(uri5);
            videov.start();
        }
    };

    Handler handler2inv2 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String videopath5 = "android.resource://com.example.salah.myapplication/" + R.raw.bonjour;
            Uri uri5 = Uri.parse(videopath5);
            videov.setVideoURI(uri5);
            videov.start();
        }
    };


    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";


        switch (matches.get(0)) {
            case ("Bonjour"):
                String videopath = "android.resource://com.example.salah.myapplication/" + R.raw.bonjour;
                Uri uri = Uri.parse(videopath);
                videov.setVideoURI(uri);
                videov.start();
                break;
            case ("vélo"):
                String videopath1 = "android.resource://com.example.salah.myapplication/" + R.raw.velo;
                Uri uri1 = Uri.parse(videopath1);
                videov.setVideoURI(uri1);
                videov.start();
                break;

            case ("madame"):
                String videopath2 = "android.resource://com.example.salah.myapplication/" + R.raw.madame;
                Uri uri2 = Uri.parse(videopath2);
                videov.setVideoURI(uri2);
                videov.start();
                break;
            case ("Bonjour Madame"):

                Runnable run=new Runnable() {
                    @Override
                    public void run() {
                        handler2.sendEmptyMessage(0);
                        Long futuretime=System.currentTimeMillis()+3000;
                        while (System.currentTimeMillis()<futuretime)
                        {
                            synchronized (this)
                            {
                                try {
                                    wait(futuretime-System.currentTimeMillis());
                                }catch (Exception ex){};
                            }
                        }
                        handler.sendEmptyMessage(0);
                    }
                };
                Thread t1=new Thread(run);
                t1.start();




            default:
                showToast(matches.get(0));

        }
        videov=(VideoView)findViewById(R.id.videoView);




        returnedText.setText(text);

    }


    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }


    private void showToast(String text)
    {
        Toast.makeText(SpeechSigns.this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
