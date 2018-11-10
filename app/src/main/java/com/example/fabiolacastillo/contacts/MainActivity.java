package com.example.fabiolacastillo.contacts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText editTextNombre;
    private TextInputEditText editTextTelefono;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextDescripcion;
    private TextInputLayout textInputNombre;
    private TextInputLayout textInputTelefono;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputDescripcion;
    public Button btnSiguiente;
    EditText t1;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        textInputNombre = findViewById(R.id.text_input_nombre);
        textInputTelefono = findViewById(R.id.text_input_telefono);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputDescripcion = findViewById(R.id.text_input_descripcion);
        setContentView(R.layout.activity_main);
        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        t1 = findViewById(R.id.editText3);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });

    }


    private void colocar_fecha() {
        t1.setText((mMonthIni + 1) + "-" + mDayIni + "-" + mYearIni+" ");
    }



    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();

                }

            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);


        }


        return null;
    }

            public void validate(View view) {
        String nombreError = null;
        if (TextUtils.isEmpty(editTextNombre.getText())) {
            nombreError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputNombre, nombreError);

        String emailError = null;
        if (TextUtils.isEmpty(editTextEmail.getText())) {
            emailError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputEmail, emailError);

        String descripcionError = null;
        if (TextUtils.isEmpty(editTextDescripcion.getText())) {
            descripcionError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputDescripcion, descripcionError);

        String telefonoError = null;
        if (TextUtils.isEmpty(editTextTelefono.getText())) {
            telefonoError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputTelefono, telefonoError);

        clearFocus();
    }

    private static void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                   String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }

    private void clearFocus() {
        View view = this.getCurrentFocus();
        if (view instanceof EditText) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();


            btnSiguiente = findViewById(R.id.buttonValidate);
            btnSiguiente.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {

        Intent explicit_intent;
        explicit_intent = new Intent(this, DisplayMessageActivity.class);
        String auxEdiNombre = Objects.requireNonNull(editTextNombre.getText()).toString();
        String auxEdiTelefono = Objects.requireNonNull(editTextTelefono.getText()).toString();
        String auxEmail = Objects.requireNonNull(editTextEmail.getText()).toString();
        String auxDescrpcion = Objects.requireNonNull(editTextDescripcion.getText()).toString();
        String auxFecha = Objects.requireNonNull(t1.getText().toString());


        explicit_intent.putExtra("nombre",auxEdiNombre);
        explicit_intent.putExtra("telefono",auxEdiTelefono);
        explicit_intent.putExtra("Email",auxEmail);
        explicit_intent.putExtra("Descripción",auxDescrpcion);
        explicit_intent.putExtra("Fecha de nacimiento", auxFecha);


        startActivity(explicit_intent);

    }

}

