package com.example.a73645.listviewtesst;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity {
    private EditText editText;
    private String gettitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Button button = (Button) findViewById(R.id.button_submit);
        final EditText edittitle = (EditText) findViewById(R.id.edit_title);
        final EditText edittext = (EditText) findViewById(R.id.edit_todo);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Log.d("MainActivity","Clicked again");
                String gettitle = edittitle.getText().toString();
                String gettext = edittext.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_title",gettitle);
                intent.putExtra("data_text",gettext);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
