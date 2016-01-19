package com.example.eironeia.parking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbHandler = new MyDBHandler(this, null, null, 1);
        int s = 15 - dbHandler.size();

        TextView size = (TextView) findViewById(R.id.num_lliures);
        String s2 = String.valueOf(s);
        size.setText(s2);


        Toast toast = Toast.makeText(getApplicationContext(), "Si tens dubtes mira l'ajuda\na la part superior!", Toast.LENGTH_SHORT);
        toast.show();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    public void onInOut(View view){
        Intent i = new Intent(this, InOut.class);
        startActivity(i);
    }

    public void onRecaptacio(View view){
        Intent i = new Intent(this, Recaptacio_eleccio.class);
        startActivity(i);
    }

    public void onExportar(View view){
        MyDBHandler dbh = new MyDBHandler(this, null, null, 1);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Coches",null);
        FileOutputStream outputStream;

        if(!cursor.moveToFirst()){
            // something went wrong - bad sql or no results
        }

        File file = new File(this.getFilesDir(), "output.csv");
        try {
            outputStream = new FileOutputStream(file);


            do{

                // if any of the columns have commas in their values, you will have to do more involved
                // checking here to ensure they are escaped properly in the csv

                // the numbes are column indexes. if you care about the order of the columns in your
                // csv, you may want to move them around

                for (int i = 0; i < 11; ++i) {
                    outputStream.write(cursor.getString(i).getBytes());
                    outputStream.write(",".getBytes());

                }
                outputStream.write(cursor.getString(11).getBytes());




            } while(cursor.moveToNext());

            outputStream.flush();
            outputStream.close();
            Toast h = Toast.makeText(this,"Fitxer creat",Toast.LENGTH_SHORT);
            h.show();

        } catch (Exception e){

            e.printStackTrace();
        }


        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            /*AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Aquest és el menú principal\n\n" +
                    "'Entrada-Sortida': Permet afegir i fer sortir un cotxe del parking\n\n" +
                    "'Recaptació': Permet veure la recaptació del parking\n\n" +
                    "'Exportat': Permet exportar la base de dades a un fitxer extern\n\n");
            dlgAlert.setTitle("Ajuda");
            dlgAlert.setPositiveButton("D'acord",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();*/
            Intent i = new Intent(this, Helper.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //here you can handle orientation change
    }

}
