package pe.anthony.spinnerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.anthony.spinnerproject.modelos.PremioVo;

public class ReportePremio extends AppCompatActivity {

    private List<String> listPremio1, listPremio2, listPremio3, listPremio4;
    private Button btn_guardarPremios;
    private EditText editTextCantidadP1, editTextCantidadP2, editTextCantidadP3, editTextCantidadP4;
    private Spinner spPremio1, spPremio2, spPremio3, spPremio4;

    private PremioVo premioVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ly_reporte_premio);
        Log.i("ReportePremio", "onCreate()");
//        Inicializacion de variables
        listPremio1 = new ArrayList<>();
        listPremio2 = new ArrayList<>();
        listPremio3 = new ArrayList<>();
        listPremio4 = new ArrayList<>();

        init();

        premioVo = new PremioVo();

        spinner(R.id.premio_1Spinner, listPremio1);
        spinner(R.id.premio_2Spinner, listPremio2);
        spinner(R.id.premio_3Spinner, listPremio3);
        spinner(R.id.premio_4Spinner, listPremio4);

        btnGuardarPremio();
        cargarDatosPrevio();

    }

    private void cargarDatosPrevio() {
        PremioVo premioVoT = (PremioVo) getIntent().getSerializableExtra("premiovoT");
        if(premioVoT!= null){
            if(premioVoT.getCantidadP1()!=0){
                editTextCantidadP1.setText(String.valueOf(premioVoT.getCantidadP1()));
                if(listPremio1.contains(premioVoT.getPremio1())){
                    String myString = premioVoT.getPremio1(); //the value you want the position for
                    setPositionSpinner(spPremio1,myString);
                }
            }if(premioVoT.getCantidadP2() !=0){
                editTextCantidadP2.setText(String.valueOf(premioVoT.getCantidadP2()));
                if(listPremio2.contains(premioVoT.getPremio2())){
                    String myString = premioVoT.getPremio2(); //the value you want the position for
                    setPositionSpinner(spPremio2,myString);
                }
            }if(premioVoT.getCantidadP3() != 0){
                editTextCantidadP3.setText(String.valueOf(premioVoT.getCantidadP3()));
                if(listPremio3.contains(premioVoT.getPremio3())){
                    String myString = premioVoT.getPremio3(); //the value you want the position for
                    setPositionSpinner(spPremio3,myString);
                }
            }if(premioVoT.getCantidadP4() != 0){
                editTextCantidadP4.setText(String.valueOf(premioVoT.getCantidadP4()));
                if(listPremio4.contains(premioVoT.getPremio4())){
                    String myString = premioVoT.getPremio4(); //the value you want the position for
                    setPositionSpinner(spPremio4,myString);
                }
            }
        }
    }

    private void setPositionSpinner(Spinner spinner, String string) {//Como su nombre dice carga de una lista de string el valor del string
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(string);
        spinner.setSelection(spinnerPosition);
    }


    private void init() {//inicializando los atributos del Activity
        editTextCantidadP1 = findViewById(R.id.editTextCantidadP1);
        editTextCantidadP2 =  findViewById(R.id.editTextCantidadP2);
        editTextCantidadP3 = findViewById(R.id.editTextCantidadP3);
        editTextCantidadP4 =  findViewById(R.id.editTextCantidadP4);

        spPremio1 =  findViewById(R.id.premio_1Spinner);
        spPremio2 =  findViewById(R.id.premio_2Spinner);
        spPremio3 =  findViewById(R.id.premio_3Spinner);
        spPremio4 =  findViewById(R.id.premio_4Spinner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void btnGuardarPremio() {
//      Estoy inicializando los view para la validacion
        btn_guardarPremios =  findViewById(R.id.btn_guardarPremios);
        btn_guardarPremios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacionCampos()) {
                    guardarDatos();
                }
            }
        });
    }

    private void guardarDatos() {
        if(spPremio1.getSelectedItemPosition() != 0){
            premioVo.setPremio1(spPremio1.getSelectedItem().toString());
            premioVo.setCantidadP1(Integer.parseInt(editTextCantidadP1.getText().toString()));
        }else{
            premioVo.setPremio1(null);
            premioVo.setCantidadP1(0);
        }
        if(spPremio2.getSelectedItemPosition() != 0){
            premioVo.setPremio2(spPremio2.getSelectedItem().toString());
            premioVo.setCantidadP2(Integer.parseInt(editTextCantidadP2.getText().toString()));
        }else{
            premioVo.setPremio2(null);
            premioVo.setCantidadP2(0);
        }
        if(spPremio3.getSelectedItemPosition() != 0){
            premioVo.setPremio3(spPremio3.getSelectedItem().toString());
            premioVo.setCantidadP3(Integer.parseInt(editTextCantidadP3.getText().toString()));
        }else{
            premioVo.setPremio3(null);
            premioVo.setCantidadP3(0);
        }
        if(spPremio4.getSelectedItemPosition() != 0){
            premioVo.setPremio4(spPremio4.getSelectedItem().toString());
            premioVo.setCantidadP4(Integer.parseInt(editTextCantidadP4.getText().toString()));
        }else{
            premioVo.setPremio4(null);
            premioVo.setCantidadP4(0);
        }
        // put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("premiovo", premioVo);
        setResult(RESULT_OK, intent);
        finish();

        Toast.makeText(ReportePremio.this, "Se guardo el premio", Toast.LENGTH_SHORT).show();
    }

    private boolean validacionCampos() {
        boolean bandera = true;
        int cantidad = 0;

        if (spPremio1.getSelectedItemPosition() != 0) {
            cantidad++;
            if (editTextCantidadP1.getText().toString().isEmpty()) {
                editTextCantidadP1.setError("Necesita ingresar la cantidad");
                bandera = false;
            }
        }

        if (spPremio2.getSelectedItemPosition() != 0) {
            cantidad++;
            if (editTextCantidadP2.getText().toString().isEmpty()) {
                editTextCantidadP2.setError("Necesita ingresar la cantidad");
                bandera = false;
            }
        }

        if (spPremio3.getSelectedItemPosition() != 0) {
            cantidad++;
            if (editTextCantidadP3.getText().toString().isEmpty()) {
                editTextCantidadP3.setError("Necesita ingresar la cantidad");
                bandera = false;
            }
        }

        if (spPremio4.getSelectedItemPosition() != 0) {
            cantidad++;
            if (editTextCantidadP4.getText().toString().isEmpty()) {
                editTextCantidadP4.setError("Necesita ingresar la cantidad");
                bandera = false;
            }
        }
        if (cantidad == 0){
            Toast.makeText(this, "Debe seleccionar un premio", Toast.LENGTH_SHORT).show();
            return false;
        }

        return bandera;
    }

    public void spinner(int IdSpinner, List<String> lista) {
        Spinner newSpinner = findViewById(IdSpinner);
        switch (IdSpinner) {
            case R.id.premio_1Spinner:
                lista.add("Premio 1");
                break;
            case R.id.premio_2Spinner:
                lista.add("Premio 2");
                break;
            case R.id.premio_3Spinner:
                lista.add("Premio 3");
                break;
            case R.id.premio_4Spinner:
                lista.add("Premio 4");
                break;
        }
        //      Carga de datos para la Lista de String
        lista.add("Maria Mercedes");
        ArrayAdapter<String> listaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newSpinner.setAdapter(listaAdapter);
    }
}
