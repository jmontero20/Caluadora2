package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText num1, num2;
    private TextView resultado;
    private Spinner combo;
    private String opciones[];
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //captura objetos
        num1 = findViewById(R.id.txtNumeroUno);
        num2 = findViewById(R.id.txtNumeroDos);
        resultado = findViewById(R.id.lblResultado);
        combo = findViewById(R.id.cmbOperaciones);
        //traemos operaciones array
        opciones = getResources().getStringArray(R.array.operaciones);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,opciones);
        combo.setAdapter(adapter);
    }

    public void calcularResultado(View v){
        if(validar()) {
            double n1, n2,res =0;
            n1 = Double.parseDouble(num1.getText().toString());
            n2 = Double.parseDouble(num2.getText().toString());
            int op = combo.getSelectedItemPosition();
            switch (op){
                case 0:
                    res = n1 + n2;
                    break;
                case 1:
                    res = n1-n2;
                    break;
                case 2:
                    res = n1*n2;
                    break;
                case 3:
                    res = n1/n2;
                    break;


            }

            resultado.setText(String.format("%.2f",res));
        }
    }

    public boolean validar(){
        if(num1.getText().toString().isEmpty()){
            num1.setError(getString(R.string.error_numero_uno));
            num1.requestFocus();
            return false;
        }

        if(num2.getText().toString().isEmpty()){
            num2.setError(getString(R.string.error_numero_dos));
            num2.requestFocus();
            return false;
        }

        double n2 = Double.parseDouble(num2.getText().toString());
        int op = combo.getSelectedItemPosition();
        if(op == 3 &&  n2 == 0){
            num2.setError(getString(R.string.mensaje_error_division_cero));
            num2.requestFocus();
            return false;
        }
        return true;
    }

    public void limpiar(View v){
        num1.setText("");
        num2.setText("");
        resultado.setText("");
        num1.requestFocus();
        combo.setSelection(0);
    }
}