package com.example.a73645.listviewtesst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Button button = (Button) findViewById(R.id.button_submit);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","Clicked again");
                Intent intent = new Intent();
                intent.putExtra("data_return","Hffff");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
