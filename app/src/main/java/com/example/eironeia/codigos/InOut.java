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
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by Eironeia on 6/11/15.
 */
public class InOut extends AppCompatActivity {

    MyDBHandler dbHandler;
    EditText matricula;
    EditText dia;
    EditText mes;
    EditText any;
    EditText hora;
    EditText minut;
    CheckBox c1;
    CheckBox c2;
    int diaU = -1;
    int mesU = -1;
    int anyU = -1;
    int horaU = -1;
    int minU = -1;
    String matU = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_out);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /** DECLARACIÓ DE VARIABLES */

        matricula = (EditText) findViewById(R.id.mat_text);
        dia  = (EditText) findViewById(R.id.dia_text);
        mes = (EditText) findViewById(R.id.mes_text);
        any = (EditText) findViewById(R.id.any_text);
        hora = (EditText) findViewById(R.id.hora_text);
        minut = (EditText) findViewById(R.id.minut_text);


        /** RECUPEREM LA BASE DE DADES **/

        dbHandler = new MyDBHandler(this, null, null, 1);
        int s = dbHandler.size();

        /** MIREM LES FILES QUE TE **/

        TextView size = (TextView) findViewById(R.id.pll_text);
        String s2 = String.valueOf(s);
        size.setText(s2);

        /** DISPLAY INTERFICIE **/

        c1 = (CheckBox) findViewById(R.id.c1);
        c2 = (CheckBox) findViewById(R.id.c2);
        final Button btn = (Button) findViewById(R.id.confirm);
        final TextView txtV = (TextView) findViewById(R.id.importt);
        final TextView txtE = (TextView) findViewById(R.id.importt_text);

        /** PREPARACIO DE PARAMETRES **/

        c1.setChecked(true); // Entrada checked

        txtE.setVisibility(View.GONE); // Import
        txtV.setVisibility(View.GONE); // Import a pagar

        /** ENTRADA CHECK BOX CHECKED **/

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) { // entrada
                c2.setChecked(false);
                c1.setChecked(b);
                btn.setText("Confirmar entrada");
                txtE.setVisibility(View.GONE); // Import
                txtV.setVisibility(View.GONE); // Import a pagar

            }
        });

        /** SALIDA CHECK BOX CHECKED **/

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // sortida
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (c2.isChecked()) c2.setChecked(true);
                c1.setChecked(false);
                c2.setChecked(b);
                btn.setText("Confirmar sortida");
                txtE.setVisibility(View.VISIBLE); // Import
                txtV.setVisibility(View.VISIBLE); // Import a pagar
            }
        });

    }


    public void agregar_clicked(View view){
        int s = dbHandler.size();
        if (dia.getText().toString().isEmpty()
                || mes.getText().toString().isEmpty()
                || any.getText().toString().isEmpty()
                || hora.getText().toString().isEmpty()
                || minut.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), " Falten omplir camps", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            int sdia =  Integer.parseInt(dia.getText().toString());
            int smes =  Integer.parseInt(mes.getText().toString());
            int sany =  Integer.parseInt(any.getText().toString());
            int shora = Integer.parseInt(hora.getText().toString());
            int sminut= Integer.parseInt(minut.getText().toString());

            if (s >= 15 && c1.isChecked()) {
                Toast toast = Toast.makeText(getApplicationContext(), "EL PARKING ESTÀ PLE", Toast.LENGTH_LONG);
                toast.show();
            }
            else if (sdia <= 0 || sdia > 31 || smes <= 0  || smes > 12 || sany < 2016 || shora < 0 || shora >= 24 || sminut < 0 || sminut > 59){
                errorInput();
            }
            else if (matricula.getText().toString().length() < 7 || matricula.getText().toString().length() > 7){
                errorInput();
            }
            else {
                if (c1.isChecked()){
                    Coche coche = new Coche(matricula.getText().toString(),
                            sdia,
                            smes,
                            sany,
                            shora,
                            sminut,
                            -1,-1,-1,-1,-1);
                    dbHandler.addCoche(coche);
                    matU = (matricula.getText().toString());
                    diaU = sdia;
                    mesU = smes;
                    anyU = sany;
                    horaU = shora;
                    minU = sminut;

                    TextView size = (TextView) findViewById(R.id.pll_text);
                    s = dbHandler.size();
                    String s2 = String.valueOf(s);
                    size.setText(s2);

                    limpiarcampos();

                    Toast toast = Toast.makeText(getApplicationContext(), "Afegit correctament!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (c2.isChecked()){

                    Cursor cursor = dbHandler.cocheByMat(matricula.getText().toString());
                    if (null != cursor && cursor.moveToFirst()) {
                        int edia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("dia")));
                        int emes = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mes")));
                        int eany = Integer.parseInt(cursor.getString(cursor.getColumnIndex("any")));
                        int ehora = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hora")));
                        int eminut = Integer.parseInt(cursor.getString(cursor.getColumnIndex("minut")));
                        if (edia <= sdia && emes <= smes && eany <= sany && ehora <= shora && eminut<= sminut){
                            String mat = cursor.getString(cursor.getColumnIndex("matricula"));
                            Coche coche = new Coche(mat,edia,emes,eany,ehora,eminut,sdia,smes,sany,shora,sminut);
                            dbHandler.borrarCoche(mat);
                            dbHandler.addCoche(coche);
                            TextView size = (TextView) findViewById(R.id.pll_text);
                            s = dbHandler.size();
                            String s2 = String.valueOf(s);
                            size.setText(s2);
                            double pt = preuTiquet(edia,emes,eany,ehora,eminut,sdia,smes,sany,shora,sminut);
                            TextView imp = (TextView) findViewById(R.id.importt_text);
                            imp.setText(Double.toString(pt));

                            Toast toast = Toast.makeText(getApplicationContext(), "Ha sortit", Toast.LENGTH_SHORT);
                            toast.show();

                            matU = (matricula.getText().toString());
                            diaU = sdia;
                            mesU = smes;
                            anyU = sany;
                            horaU = shora;
                            minU = sminut;

                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "El dia de sortida ha de ser posterior al d'entrada", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "No està el cotxe aquest al parking", Toast.LENGTH_SHORT);
                        toast.show();

                    }

                }

            }
        }

    }


    public void errorInput(){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Format:\nMatricula:1234ABC\nHora: [0-23], Minut [0-59]\nDia [1-31], Mes [1-12]\nAny [2016-?] ");
        dlgAlert.setTitle("Error en afegir Cotxe");
        dlgAlert.setPositiveButton("Reintentar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    //Limpia los valores entrados para efectos de estetica
    public void limpiarcampos(){

       /* matricula.setText("");
        dia.setText("");
        mes.setText("");
        any.setText("");
        hora.setText("");
        minut.setText("");*/

    }
    
    public void onDelete(View view){
        dbHandler.deleteAll();
        int s = dbHandler.size();
        TextView size = (TextView) findViewById(R.id.pll_text);
        String s2 = String.valueOf(s);
        size.setText(s2);

    }

    public void onListar(View view){
        if (c1.isChecked()){
            Intent i = new Intent(this, ListCoches.class);
            startActivity(i);
        }
        else if (c2.isChecked()){
            Intent i = new Intent(this, ListCochesSortida.class);
            startActivity(i);
        }

    }

    public void onUndo(View view){
        if (c1.isChecked()) dbHandler.deleteCoche(matU,diaU,mesU,anyU,horaU,minU);
        else if (c2.isChecked()) dbHandler.addCoche(new Coche(matricula.getText().toString(),
                diaU,
                mesU,
                anyU,
                horaU,
                minU,
                -1,-1,-1,-1,-1));
        int s = dbHandler.size();
        String s2 = String.valueOf(s);
        TextView size = (TextView) findViewById(R.id.pll_text);
        size.setText(s2);


    }

    public double preuTiquet(int edia,int emes,int eany,int ehora,int eminut,int sdia,int smes,int sany,int shora,int sminut){
        int rdia = sdia - edia;
        int rmes = smes - emes;
        int rany = sany - eany;
        int rhora = shora - ehora;
        int rminut = sminut - eminut;

        double result = (rminut) + (rhora * 24) + (rdia * 24 * 60) + (rmes * 30 * 24 * 60) + (rany * 12 * 30 * 24 * 60);
        result *= 0.02;
        return  result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //here you can handle orientation change
    }



}
