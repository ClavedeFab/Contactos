package com.example.fabiolacastillo.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    TextView etiNombre, etiFecha, etiTelefono, etiEmail, etiDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        etiNombre = findViewById(R.id.eti_nombreA);
        etiFecha = findViewById(R.id.eti_fechaA);
        etiEmail = findViewById(R.id.eti_emailA);
        etiDescripcion = findViewById(R.id.eti_descripcionA);

        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {
            String datoNombre=(String)extras.get("nombre");
            String datoTelefono= (String) extras.get("telefono");
            String datoFecha = (String) extras.get("Fecha de nacimiento");
            String datoEmail = (String) extras.get("Email");
            String datoDescripcion = (String) extras.get("Descripción");


            etiNombre.setText(datoNombre);
            etiFecha.setText(datoFecha);
            etiTelefono.setText(datoTelefono);
            etiEmail.setText(datoEmail);
            etiDescripcion.setText(datoDescripcion);
        }
    }
}
