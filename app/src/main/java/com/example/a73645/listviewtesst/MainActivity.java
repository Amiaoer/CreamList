package com.example.a73645.listviewtesst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private String[] data = {"today","yestoday","morning","afternoon","evening"};
    List<dayline> daylineList = new ArrayList<>();
    DayLineAdapter adapter;
    ListView listView;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK)
                {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("MainActivity",returnedData);
                    dayline today = new dayline(returnedData,R.drawable.test_pic);
                    daylineList.add(today);
                    adapter.notifyDataSetChanged();
                    Log.d("MainActivity",returnedData);
                }

                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initdayline();

        adapter = new DayLineAdapter(MainActivity.this,R.layout.dayline_item,daylineList);
        listView = (ListView) findViewById(R.id.list_View);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                dayline daylin = daylineList.get(position);
                Toast.makeText(MainActivity.this,daylin.getName(),Toast.LENGTH_SHORT).show();
            }

        });
        Button button = (Button) findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                startActivityForResult(intent,1);
                Log.d("MainActivity","Clicked");
            }
        });


    }

    private void initdayline()
    {
        for (int i=0;i<2;i++)
        {
            dayline today = new dayline("today",R.drawable.test_pic);
            daylineList.add(today);
            dayline morning = new dayline("morning",R.drawable.test_pic);
            daylineList.add(morning);
            dayline yestoday = new dayline ("yestoday",R.drawable.test_pic);
            daylineList.add(yestoday);
        }
    }
}
