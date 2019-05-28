package com.example.root.SignsLanguage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuChoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choix);
    }
    public void writeTypeOption(View view) {
        final Intent signsOption =new Intent(this,SpeechTextContinuously.class);
        startActivity(signsOption);
        finish();
    }
    public void signsTypeOption(View view) {
        final Intent signsOption = new Intent(this, SpeechSigns.class);
        startActivity(signsOption);
        finish();
    }
}
