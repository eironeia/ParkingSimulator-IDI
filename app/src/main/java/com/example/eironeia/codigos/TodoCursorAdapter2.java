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

    public class TodoCursorAdapter2 extends CursorAdapter {
        public TodoCursorAdapter2(Context context, Cursor cursor) {
            super(context, cursor, 0);

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_recaptacio, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            TextView mattxt = (TextView) view.findViewById(R.id.mat_txt);
            TextView horatxt = (TextView) view.findViewById(R.id.hora_txt);
            TextView minuttxt = (TextView) view.findViewById(R.id.minut_txt);
            TextView horastxt = (TextView) view.findViewById(R.id.horas_txt);
            TextView minutstxt = (TextView) view.findViewById(R.id.minuts_txt);


            // Extraccion de las propiedades del cursor

           //int txtid = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
           String txtmat = cursor.getString(cursor.getColumnIndexOrThrow("matricula"));
            int txthora = cursor.getInt(cursor.getColumnIndexOrThrow("hora"));
            int txtminut = cursor.getInt(cursor.getColumnIndexOrThrow("minut"));
            int txthoras = cursor.getInt(cursor.getColumnIndexOrThrow("horaSortida"));
            int txtminuts = cursor.getInt(cursor.getColumnIndexOrThrow("minutSortida"));


           //idtxt.setText(String.valueOf(txtid));
            mattxt.setText(txtmat);
            horatxt.setText("      "+String.valueOf(txthora)+"      ");
            minuttxt.setText(String.valueOf(txtminut)+"      ");
            horastxt.setText(String.valueOf(txthoras)+"      ");
            minutstxt.setText(String.valueOf(txtminuts)+"      ");

        }

    }



