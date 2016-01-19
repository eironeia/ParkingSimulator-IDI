package com.example.eironeia.parking;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by A on 01/05/2015.
 */


    public class TodoCursorAdapter extends CursorAdapter {
        public TodoCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_coches, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            //TextView idtxt = (TextView) view.findViewById(R.id.id_txt);
            TextView mattxt = (TextView) view.findViewById(R.id.mat_txt);
            TextView diatxt = (TextView) view.findViewById(R.id.dia_txt);
            TextView mestxt = (TextView) view.findViewById(R.id.mes_txt);
            TextView anytxt = (TextView) view.findViewById(R.id.any_txt);
            TextView horatxt = (TextView) view.findViewById(R.id.hora_txt);
            TextView minuttxt = (TextView) view.findViewById(R.id.minut_txt);


            // Extraccion de las propiedades del cursor

           //int txtid = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
           String txtmat = cursor.getString(cursor.getColumnIndexOrThrow("matricula"));
           int txtdia = cursor.getInt(cursor.getColumnIndexOrThrow("dia"));
            int txtmes = cursor.getInt(cursor.getColumnIndexOrThrow("mes"));
            int txtany = cursor.getInt(cursor.getColumnIndexOrThrow("any"));
             int txthora = cursor.getInt(cursor.getColumnIndexOrThrow("hora"));
            int txtminut = cursor.getInt(cursor.getColumnIndexOrThrow("minut"));

           //idtxt.setText(String.valueOf(txtid));
            mattxt.setText(String.valueOf(txtmat));
            diatxt.setText(String.valueOf(txtdia)+"      ");
            mestxt.setText(String.valueOf(txtmes)+"      ");
            anytxt.setText(String.valueOf(txtany)+"      ");
            horatxt.setText(String.valueOf(txthora)+"      ");
            minuttxt.setText(String.valueOf(txtminut));


        }
    }



