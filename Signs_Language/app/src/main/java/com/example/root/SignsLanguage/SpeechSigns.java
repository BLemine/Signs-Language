package com.example.root.SignsLanguage;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import java.util.ArrayList;

public class SpeechSigns extends AppCompatActivity implements
        RecognitionListener  {
    private VideoView videov;

    private static final int REQUEST_RECORD_PERMISSION = 100;
    private TextView returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    //

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_transc);

        returnedText = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progressBar1);
        toggleButton = findViewById(R.id.toggleButton1);
        videov= findViewById(R.id.videoView);

        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
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


    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }


    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
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

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }


    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";

        String videopath = "android.resource://com.example.root.SignsLanguage/" + R.raw.bonjour;
        String videopath2 = "android.resource://com.example.root.SignsLanguage/" + R.raw.madame;
        String videopath1 = "android.resource://com.example.root.SignsLanguage/" + R.raw.velo;
        String videopath111 = "android.resource://com.example.root.SignsLanguage/" + R.raw.au_revoir;
        String videopath12 = "android.resource://com.example.root.SignsLanguage/" + R.raw.aujourdhui;
        String videopath13 = "android.resource://com.example.root.SignsLanguage/" + R.raw.avec;
        String videopath14 = "android.resource://com.example.root.SignsLanguage/" + R.raw.bien;
        String videopath15 = "android.resource://com.example.root.SignsLanguage/" + R.raw.de_rien;
        String videopath16 = "android.resource://com.example.root.SignsLanguage/" + R.raw.ca_va;
        String videopath17 = "android.resource://com.example.root.SignsLanguage/" + R.raw.demain;
        String videopath18 = "android.resource://com.example.root.SignsLanguage/" + R.raw.eleve;
        String videopath19 = "android.resource://com.example.root.SignsLanguage/" + R.raw.formation;
        String videopath20 = "android.resource://com.example.root.SignsLanguage/" + R.raw.jai;
        String videopath21 = "android.resource://com.example.root.SignsLanguage/" + R.raw.merci;
        String videopath22 = "android.resource://com.example.root.SignsLanguage/" + R.raw.maintenant;
        String videopath23 = "android.resource://com.example.root.SignsLanguage/" + R.raw.objet;
        String videopath24 = "android.resource://com.example.root.SignsLanguage/" + R.raw.prenom;
        String videopath25 = "android.resource://com.example.root.SignsLanguage/" + R.raw.ville;
        String videopath26 = "android.resource://com.example.root.SignsLanguage/" + R.raw.travail;
        String videopath27 = "android.resource://com.example.root.SignsLanguage/" + R.raw.cafe;

        for (String result : matches)
            text += result + "\n";

        switch (matches.get(0).toLowerCase()) {
            case ("cafe"):
                Uri uri27 = Uri.parse(videopath27);
                videov.setVideoURI(uri27);
                videov.start();
                break;
            case ("travail"):
                Uri uri26 = Uri.parse(videopath26);
                videov.setVideoURI(uri26);
                videov.start();
                break;
            case ("ville"):
                Uri uri25 = Uri.parse(videopath25);
                videov.setVideoURI(uri25);
                videov.start();
                break;
            case ("prenom"):
                Uri uri24 = Uri.parse(videopath24);
                videov.setVideoURI(uri24);
                videov.start();
                break;
            case ("objet"):
                Uri uri23 = Uri.parse(videopath23);
                videov.setVideoURI(uri23);
                videov.start();
                break;
            case ("maintenant"):
                Uri uri22 = Uri.parse(videopath22);
                videov.setVideoURI(uri22);
                videov.start();
                break;
            case ("merci"):
                Uri uri21 = Uri.parse(videopath21);
                videov.setVideoURI(uri21);
                videov.start();
                break;
            case ("j'ai"):
                Uri uri20 = Uri.parse(videopath20);
                videov.setVideoURI(uri20);
                videov.start();
                break;
            case ("formation"):
                Uri uri19 = Uri.parse(videopath19);
                videov.setVideoURI(uri19);
                videov.start();
                break;
            case ("eleve"):
                Uri uri18 = Uri.parse(videopath18);
                videov.setVideoURI(uri18);
                videov.start();
                break;
            case ("demain"):
                Uri uri17 = Uri.parse(videopath17);
                videov.setVideoURI(uri17);
                videov.start();
                break;
            case ("ça va"):
                Uri uri16 = Uri.parse(videopath16);
                videov.setVideoURI(uri16);
                videov.start();
                break;
            case ("de rien"):
                Uri uri15 = Uri.parse(videopath15);
                videov.setVideoURI(uri15);
                videov.start();
                break;
            case ("bien"):
                Uri uri14 = Uri.parse(videopath14);
                videov.setVideoURI(uri14);
                videov.start();
                break;
            case ("avec"):
                Uri uri13 = Uri.parse(videopath13);
                videov.setVideoURI(uri13);
                videov.start();
                break;
            case ("aujourd'hui"):
                Uri uri12 = Uri.parse(videopath12);
                videov.setVideoURI(uri12);
                videov.start();
                break;
            case ("bonjour"):
                Uri uri = Uri.parse(videopath);
                videov.setVideoURI(uri);
                videov.start();
                break;
            case ("au_revoir"):
                Uri uri111 = Uri.parse(videopath111);
                videov.setVideoURI(uri111);
                videov.start();
                break;
            case ("vélo"):
                Uri uri1 = Uri.parse(videopath1);
                videov.setVideoURI(uri1);
                videov.start();
                break;
            case ("madame"):
                Uri uri2 = Uri.parse(videopath2);
                videov.setVideoURI(uri2);
                videov.start();
                break;
            case ("bonjour madame"):
                @SuppressLint("HandlerLeak")
                final Handler handler = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        String videopath05 = "android.resource://com.example.root.SignsLanguage/" + R.raw.madame;
                        Uri uri5 = Uri.parse(videopath05);
                        videov.setVideoURI(uri5);
                        videov.start();
                    }
                };
                @SuppressLint("HandlerLeak")
                final Handler handler2 = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        String videopath04 = "android.resource://com.example.root.SignsLanguage/" + R.raw.bonjour;
                        Uri uri5 = Uri.parse(videopath04);
                        videov.setVideoURI(uri5);
                        videov.start();
                    }
                };
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
                break;
            case ("demain j'ai une formation"):
                @SuppressLint("HandlerLeak")
                final Handler handler10 = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        String videopath30 = "android.resource://com.example.root.SignsLanguage/" + R.raw.demain;
                        Uri uri30 = Uri.parse(videopath30);
                        videov.setVideoURI(uri30);
                        videov.start();
                    }
                };
                @SuppressLint("HandlerLeak")
                final Handler handler20 = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        String videopath31 = "android.resource://com.example.root.SignsLanguage/" + R.raw.jai;
                        Uri uri31 = Uri.parse(videopath31);
                        videov.setVideoURI(uri31);
                        videov.start();
                    }
                };
                @SuppressLint("HandlerLeak")
                final Handler handler19 = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        String videopath19 = "android.resource://com.example.root.SignsLanguage/" + R.raw.formation;
                        Uri uri19 = Uri.parse(videopath19);
                        videov.setVideoURI(uri19);
                        videov.start();
                    }
                };
                Runnable run10=new Runnable() {
                    @Override
                    public void run() {
                        handler20.sendEmptyMessage(0);
                        Long futuretime=System.currentTimeMillis()+6000;
                        while (System.currentTimeMillis()<futuretime)
                        {
                            synchronized (this)
                            {
                                try {
                                    wait(futuretime-System.currentTimeMillis());
                                }catch (Exception ex){};
                            }
                            handler10.sendEmptyMessage(0);

                        }
                        /*Long futuretime1=System.currentTimeMillis()+3000;
                        while (System.currentTimeMillis()<futuretime)
                        {
                            synchronized (this)
                            {
                                try {
                                    wait(futuretime-System.currentTimeMillis());
                                }catch (Exception ex){};
                            }
                            handler19.sendEmptyMessage(0);
                        }*/
                    }
                };
                Thread t10=new Thread(run10);
                t10.start();
                break;
            default:
                showToast(matches.get(0));
        }

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

}




