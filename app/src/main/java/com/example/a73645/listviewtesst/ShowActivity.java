package com.example.a73645.listviewtesst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        EditText texttitle = (EditText) findViewById(R.id.text_title);
        EditText texttodo = (EditText) findViewById(R.id.text_todo);


        Intent intent = getIntent();
        String text_title = intent.getStringExtra("text_title");
        String text_text = intent.getStringExtra("text_text");
        texttitle.setText(text_title);
        texttodo.setText(text_text);
    }
}
