package com.example.root.SignsLanguage;
/*
*AUTHOR  : BAILLAHI
*
*
* */
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpeechTextContinuously extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView txvResult;
    //
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private static final int REQUEST_RECORD_PERMISSION = 100;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer recognizer;
    private RecognitionListener listener;
    private Intent intent;
    private Spinner dropdown;
    private ArrayAdapter<String> adapter;
    private String[] items;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        items = new String[]{"Anglais", "Français", "Arabe"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        //
        txvResult =findViewById(R.id.txvResult);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //Toast.makeText(getApplicationContext(),dropdown.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
        switch (dropdown.getSelectedItem().toString()){ // to change speech recognition language inpit
            case "Anglais":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                break;
            case "Français":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR");
                break;
            case "Arabe":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-MR");
                break;
        }
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR"); this line for changing the language
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.example.root.voicecontinuous");
        //startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        recognizer = SpeechRecognizer.createSpeechRecognizer(this.getApplicationContext());
        listener = new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
            }

            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            // this method is used for instantanuous speech recognition
            public void onPartialResults(Bundle partialResults) {
                //The main method required. Just keep printing results in this.
                ArrayList<String> returnedSpeech = partialResults
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for(String str:returnedSpeech){
                    txvResult.setText(""+str);
                }
                //txvResult.setText("Speech : "+returnedSpeech.toString());
                Log.i(LOG_TAG, "onPartialResults");

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        };

        recognizer.setRecognitionListener(listener);
        //recognizer.startListening(intent);
        /*speech.setRecognitionListener(listener);
        speech.startListening(recognizerIntent);*/
        //

    }
    public void getSpeechInput(View view) {
        //ActivityCompat.requestPermissions(SpeechTextContinuously.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
        //speech.startListening(recognizerIntent);
        recognizer.setRecognitionListener(listener);
        recognizer.startListening(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (dropdown.getSelectedItem().toString()){ // to change speech recognition language inpit
            case "Anglais":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                Toast.makeText(getApplicationContext(),"Language : English",Toast.LENGTH_LONG).show();
                break;
            case "Français":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR");
                Toast.makeText(getApplicationContext(),"Langue : Français",Toast.LENGTH_LONG).show();
                break;
            case "Arabe":
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-MR");
                Toast.makeText(getApplicationContext(),"اللغة : "+items[position],Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
