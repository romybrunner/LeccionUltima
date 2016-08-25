package com.example.estudiante.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    VistaJuego oJuego;
    TextView etiNombre;
    String datoNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   /*
     etiNombre = (TextView) findViewById( R.id.labelnombre);
        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            datoNombre=(String)extras.get("nombre");//Obtengo el nombre
            etiNombre.setText(datoNombre);
        }

*/
        try{
            oJuego = new VistaJuego(MainActivity.this);
            setContentView(oJuego);
        }catch (Exception e){
            setContentView(new VistaJuego(MainActivity.this));
        }


    }


}