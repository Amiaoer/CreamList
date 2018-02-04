package com.example.a73645.listviewtesst;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private String[] data = {"today","yestoday","morning","afternoon","evening"};
    List<dayline> daylineList = new ArrayList<>();
    DayLineAdapter adapter;
    ListView listView;
    ContentValues values = new ContentValues();
    private MyDatabaseHelper dbHelper;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK)
                {
                    String datatitle = data.getStringExtra("data_title");
                    String datatext = data.getStringExtra("data_text");
                    Log.d("MainActivity",datatitle);
                    dayline today = new dayline(datatitle,R.drawable.test_pic);
                    daylineList.add(today);
                    adapter.notifyDataSetChanged();
                    Log.d("MainActivity",datatitle);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    values.clear();
                    values.put("title",datatitle);
                    values.put("todo",datatext);
                    db.insert("List",null,values);
                    values.clear();
                }

                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLiteDatabase db;
        initdayline();


        Button buttoninit = (Button) findViewById(R.id.button_init);
        buttoninit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("List",null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    do{
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String todo = cursor.getString(cursor.getColumnIndex("todo"));
                        dayline today = new dayline(title,R.drawable.test_pic);
                        daylineList.add(today);
                        Log.d("MainActivity", title);
                        adapter.notifyDataSetChanged();
                    }while(cursor.moveToNext());
                }
                cursor.close();
                adapter.notifyDataSetChanged();
            }
        });

        //Cursor cursor = db.query("List",null,null,null,null,null,null);

        adapter = new DayLineAdapter(MainActivity.this,R.layout.dayline_item,daylineList);
        listView = (ListView) findViewById(R.id.list_View);
        listView.setAdapter(adapter);
        ListView listView = (ListView) findViewById(R.id.list_View);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                dayline dayline = daylineList.get(position);
                Toast.makeText(MainActivity.this,dayline.getName(),Toast.LENGTH_SHORT).show();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursortitle = db.query("List",null,"title like ?",new String[]{dayline.getName()},null,null,null);

                if(cursortitle.moveToLast())
                {
                    do{
                        String title = cursortitle.getString(cursortitle.getColumnIndex("title"));
                        String todo = cursortitle.getString(cursortitle.getColumnIndex("todo"));
                        Intent intentt = new Intent(MainActivity.this,ShowActivity.class);
                        intentt.putExtra("text_title",title);
                        intentt.putExtra("text_text",todo);
                        startActivity(intentt);

                    }while(cursortitle.moveToNext());
                }
                cursortitle.close();
                adapter.notifyDataSetChanged();
            }
        });
        dbHelper = new MyDatabaseHelper(this,"ListStore.db",null,1);
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

    protected void initdayline()
    {
    }

}
