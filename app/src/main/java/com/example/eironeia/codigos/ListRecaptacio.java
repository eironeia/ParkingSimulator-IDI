package com.example.eironeia.parking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.ListView;


public class ListRecaptacio extends ActionBarActivity {

    EditText inputSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recaptacio);

        int d = Integer.parseInt(getIntent().getExtras().getString("dia"));
        int m = Integer.parseInt(getIntent().getExtras().getString("mes"));
        int a = Integer.parseInt(getIntent().getExtras().getString("any"));
        int h = Integer.parseInt(getIntent().getExtras().getString("hora"));
        int min = Integer.parseInt(getIntent().getExtras().getString("minut"));

        MyDBHandler dbHandler;
        dbHandler = new MyDBHandler(this, null, null, 1);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor cursor = dbHandler.listarRecaptacioDia(d,m,a,h);

        ListView lvlitems = (ListView) findViewById(R.id.lvlitems);
        lvlitems.setTextFilterEnabled(true);
        final TodoCursorAdapter2 todoAdapter = new TodoCursorAdapter2(this, cursor);
        lvlitems.setAdapter(todoAdapter);

    }
}
