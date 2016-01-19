package com.example.eironeia.parking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eironeia on 25/12/15.
 */
public class Recaptacio_Dates extends AppCompatActivity{

    MyDBHandler dbHandler;

    EditText dia ;
    EditText mes ;
    EditText any ;
    EditText hora ;

    EditText dia2;
    EditText mes2;
    EditText any2;
    EditText hora2;

    TextView imp ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recaptacio_2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHandler = new MyDBHandler(this, null, null, 1);

        dia = (EditText) findViewById(R.id.dia_text);
        mes = (EditText) findViewById(R.id.mes_text);
        any = (EditText) findViewById(R.id.any_text);
        hora = (EditText) findViewById(R.id.hora_text);

        dia2 = (EditText) findViewById(R.id.dia_text2);
        mes2 = (EditText) findViewById(R.id.mes_text2);
        any2 = (EditText) findViewById(R.id.any_text2);
        hora2 = (EditText) findViewById(R.id.hora_text2);

        imp = (TextView) findViewById(R.id.importt_text);

    }

    public void veureRecaptacionsDates(View view){
        int sdia, smes, sany, shora;
        int sdia2, smes2, sany2, shora2;


        if (dia.getText().toString().isEmpty()
                || mes.getText().toString().isEmpty()
                || any.getText().toString().isEmpty()
                || hora.getText().toString().isEmpty()
                || dia2.getText().toString().isEmpty()
                || mes2.getText().toString().isEmpty()
                || any2.getText().toString().isEmpty()
                || hora2.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), " Falten omplir camps!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            sdia = Integer.parseInt(dia.getText().toString());
            smes = Integer.parseInt(mes.getText().toString());
            sany = Integer.parseInt(any.getText().toString());
            shora = Integer.parseInt(hora.getText().toString());

            sdia2 = Integer.parseInt(dia2.getText().toString());
            smes2 = Integer.parseInt(mes2.getText().toString());
            sany2 = Integer.parseInt(any2.getText().toString());
            shora2 = Integer.parseInt(hora2.getText().toString());

            double pt = 0.0;
            if (sdia <= 0 || sdia > 31 || smes <= 0  || smes > 12 || sany < 2016 || shora < 0 || shora >= 24 || sdia2 <= 0 || sdia2 > 31 || smes2 <= 0  || smes2 > 12 || sany2 < 2016 || shora2 < 0 || shora2 >= 24){
                errorInputDates();
            }
            else {
                Cursor cursor = dbHandler.listarRecaptacioDates(smes, sany, smes2, sany2);
                while (!cursor.isAfterLast()){
                    int edia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("dia")));
                    int emes = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mes")));
                    int eany = Integer.parseInt(cursor.getString(cursor.getColumnIndex("any")));
                    int ehora = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hora")));
                    int eminut = Integer.parseInt(cursor.getString(cursor.getColumnIndex("minut")));

                    int ssdia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("diaSortida")));
                    int ssmes = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mesSortida")));
                    int ssany = Integer.parseInt(cursor.getString(cursor.getColumnIndex("anySortida")));
                    int sshora = Integer.parseInt(cursor.getString(cursor.getColumnIndex("horaSortida")));
                    int ssminut = Integer.parseInt(cursor.getString(cursor.getColumnIndex("minutSortida")));

                    pt += (preuTiquetDates(edia, emes, eany, ehora, eminut, ssdia, ssmes, ssany, sshora, ssminut));

                    cursor.moveToNext();
                }
                imp.setText(String.valueOf(pt));

            }

        }
    }

    public double preuTiquetDates(int edia,int emes,int eany,int ehora,int eminut,int sdia,int smes,int sany,int shora,int sminut){
        int rdia = sdia - edia;
        int rmes = smes - emes;
        int rany = sany - eany;
        int rhora = shora - ehora;
        int rminut = sminut - eminut;

        double result = (rminut) + (rhora * 24) + (rdia * 24 * 60) + (rmes * 30 * 24 * 60) + (rany * 12 * 30 * 24 * 60);
        result *= 0.02;
        return  result;
    }

    public boolean dinsHorariDates(int horaS, int minS, int h, int m){
        int r1 = (horaS * 60) + (minS);
        int r2 = (h*60) + (m);
        return (r1 <= r2);
    }

    public void onListarDates(View view){
        if (dia.getText().toString().isEmpty()
                || mes.getText().toString().isEmpty()
                || any.getText().toString().isEmpty()
                || hora.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), " Falten omplir camps!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            int sdia, smes, sany, shora;
            int sdia2, smes2, sany2, shora2;

            sdia = Integer.parseInt(dia.getText().toString());
            smes = Integer.parseInt(mes.getText().toString());
            sany = Integer.parseInt(any.getText().toString());
            shora = Integer.parseInt(hora.getText().toString());

            sdia2 = Integer.parseInt(dia2.getText().toString());
            smes2 = Integer.parseInt(mes2.getText().toString());
            sany2 = Integer.parseInt(any2.getText().toString());
            shora2 = Integer.parseInt(hora2.getText().toString());

            if (sdia <= 0 || sdia > 31 || smes <= 0  || smes > 12 || sany < 2016 || shora < 0 || shora >= 24 || sdia2 <= 0 || sdia2 > 31 || smes2 <= 0  || smes2 > 12 || sany2 < 2016 || shora2 < 0 || shora2 >= 24){
                errorInputDates();
            }

            else {
                Intent i = new Intent(this, ListRecaptacioDates.class);
                i.putExtra("dia", mes.getText().toString());
                i.putExtra("mes", any.getText().toString());
                i.putExtra("any", mes2.getText().toString());
                i.putExtra("hora", any2.getText().toString());
                i.putExtra("minut", "12");
                startActivity(i);
            }
        }

    }



    public void errorInputDates(){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Format: \nHora: [0-23], Minut [0-59]\nDia [1-31], Mes [1-12]\nAny [2016-?] ");
        dlgAlert.setTitle("Error en Afegir Persona");
        dlgAlert.setPositiveButton("Reintentar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //here you can handle orientation change
    }
}
