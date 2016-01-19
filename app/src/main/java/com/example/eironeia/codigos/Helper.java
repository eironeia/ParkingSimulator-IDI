package com.example.eironeia.parking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Helper extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);
        TextView text = (TextView) findViewById(R.id.txt);
        text.setText("En el menú principal tenim diverses opcions\n\n" +
                "- 'Entrada-Sortida': Permet afegir i fer sortir un cotxe del parking\n\n" +
                "- 'Recaptació': Permet veure la recaptació del parking\n\n" +
                "- 'Exportat': Permet exportar la base de dades a un fitxer extern\n\n\n" +
                "Menu 'Entrada-Sortida'\n\n" +
                "Hi han dos checkBox els quals defineixen\n si estas fent una entrada d'un vehicle o bé una sortida.\n\n" +
                "S'afegiran els camps corresponents que són\n la matrícula, hora[0-24], minut[0-60], Dia [1-31], Mes [1-12], Any [2016-?].\n\n" +
                "S'ha de seguir el format d'entrada dels camps que s'especifica,\n l'aplicació comprova que totes les dades tinguin\n " +
                "el format corresponent però sinó es fiquen com s'escau\n els resultats poden ser diferents dels esperats.\n\n" +
                "Entrada checkBox Checked:\n\n" +
                "- El botó de confirmació d'entrada afegeix el cotxe al parking i\n s'incrementa en 1 les places ocupades del parking.\n\n" +
                "- El botó de llistar cotxes llista tots els cotxes\n que hi han al parking que encara no han sortit.\n" +
                "- El botó eliminar tots els cotxes permet eliminar\n com bé diu el seu nom eliminar tots els cotxes que estan\n o han estat al parking.\n\n" +
                "Sortida checBox Checked:\n\n" +
                "Exactament igual que l'entrada però en comptes d'afegir surt el cotxe especificat\n" +
                "Llista els cotxes que ja han sortit\n" +
                "Elimina tots els cotxes com a l'entrada\n" +
                "Apareix un nou camp import a pagar que fica el preu\nque el cotxe ha de pagar\n\n" +
                "Menu Recaptació:\n\n" +
                "Ens deriva a dos noves opcions Recaptació del dia i Recaptacio de dates\n Permet obtenir la recaptació d'un dia o entre dues dates\n"+
                "\nRecaptació del dia: \n\nDefinim els valors que demana l'aplicació i obtenim un llistat i la recaptació d'un dia concret\n" +
                "\n\nRecaptacio de dates: \n\nDefinim els valors que demana l'aplicació i obtenim un llistat i la recaptació d'un dia concret\n"

                );

    }




}
