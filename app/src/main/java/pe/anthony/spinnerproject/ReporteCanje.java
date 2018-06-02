package pe.anthony.spinnerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.anthony.spinnerproject.modelos.CanjeVo;
import pe.anthony.spinnerproject.modelos.PremioVo;

public class ReporteCanje extends AppCompatActivity {

    public static final int REQUEST_CANJE = 1;
    private List<String> listCategoria;
    private Button btnPremio, btnGuardarCanje;
    private EditText editTextPrecio, editTextCantidad;
    private CanjeVo canjeVo;
    private PremioVo premioVoTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ly_reporte_canje);

        canjeVo = new CanjeVo();

        listCategoria = new ArrayList<>();
        listCategoria.add("Seleccione");
        listCategoria.add("Maria Mercedes");

        editTextPrecio();
        btnPremio();

        spinner(R.id.categoriaSpinner, listCategoria);
        spinner(R.id.marcaSpinner, listCategoria);
        spinner(R.id.presentacionSpinner, listCategoria);

        btnGuardarCanje();
    }

    private void btnGuardarCanje() {
        btnGuardarCanje =  findViewById(R.id.btnGuardarCanje);
        editTextCantidad =  findViewById(R.id.editTextCantidad);

        btnGuardarCanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacionCampos()) {
                    //Toast.makeText(ReporteCanje.this, "Exito", Toast.LENGTH_SHORT).show();
                    guardarDatos();
                }
            }
        });
    }

    private void guardarDatos() {

        Spinner spinnerCategoria =  findViewById(R.id.categoriaSpinner);
        canjeVo.setCategoria(spinnerCategoria.getSelectedItem().toString());

        Spinner spinnerMarca =  findViewById(R.id.marcaSpinner);
        canjeVo.setMarca(spinnerMarca.getSelectedItem().toString());

        Spinner spinnerPresentacion =  findViewById(R.id.presentacionSpinner);
        canjeVo.setPresentacion(spinnerPresentacion.getSelectedItem().toString());

        EditText editTextCantidad =  findViewById(R.id.editTextCantidad);
        canjeVo.setCantidad(Integer.parseInt(editTextCantidad.getText().toString()));

        EditText editTextPrecio =  findViewById(R.id.editTextPrecio);
        canjeVo.setPrecio(Double.parseDouble(editTextPrecio.getText().toString()));

        Intent intentPremio = getIntent();
        if (intentPremio.hasExtra("premiovo")) {
            canjeVo.setPremioVo((PremioVo) intentPremio.getSerializableExtra("premiovo"));
        }

        Toast.makeText(ReporteCanje.this, "Se Guardo Exitosamente", Toast.LENGTH_SHORT).show();
    }

    private boolean validacionCampos() {

        boolean validateEditTxt_Cantidad = validacionCampoEditText(editTextCantidad);
        boolean validateEditTxt_Precio = validacionCampoEditText(editTextPrecio);
        boolean validateSpinner_Cat = validacionCampoSpinner(R.id.categoriaSpinner);
        boolean validateSpinner_Mar = validacionCampoSpinner(R.id.marcaSpinner);
        boolean validateSpinner_Pre = validacionCampoSpinner(R.id.presentacionSpinner);

        if (premioVoTest == null) {
            Toast.makeText(ReporteCanje.this, "Falta agregar Premio", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validateEditTxt_Cantidad && !validateEditTxt_Precio  &&
                !validateSpinner_Cat  && !validateSpinner_Mar  &&
                !validateSpinner_Pre ) {
            return false;
        } else if (!validateEditTxt_Cantidad || !validateEditTxt_Precio) {
            return false;
        }
        return true;
    }

    private boolean validacionCampoEditText(EditText editText) {
        if (editText.getId() == R.id.editTextCantidad) {
            String strEditText = editText.getText().toString();
            if (TextUtils.isEmpty(strEditText)) {
                editText.setError("Necesita ingresar la cantidad");
                return false;
            }
        }
        if (editText.getId() == R.id.editTextPrecio) {
            String strEditText = editText.getText().toString();
            if (TextUtils.isEmpty(strEditText)) {
                editText.setError("Necesita ingresar el Precio");
                return false; //false -> significa que esta incorrecto
            }
        }
        return true; //true -> significa que esta todo correcto
    }

    private boolean validacionCampoSpinner(int IdSpinner) {
        switch (IdSpinner) {
            case R.id.categoriaSpinner:
                Spinner validateSpinner1 =  findViewById(IdSpinner);
                int positionSpiner1 = validateSpinner1.getSelectedItemPosition();
                if (positionSpiner1 == 0) {
                    return setErrorSpinner(validateSpinner1);
                } else return true;
            case R.id.marcaSpinner:
                Spinner validateSpinner2 = findViewById(IdSpinner);
                int positionSpiner2 = validateSpinner2.getSelectedItemPosition();
                if (positionSpiner2 == 0) {
                    return setErrorSpinner(validateSpinner2);
                } else return true;
            case R.id.presentacionSpinner:
                Spinner validateSpinner3 =  findViewById(IdSpinner);
                int positionSpiner3 = validateSpinner3.getSelectedItemPosition();
                if (positionSpiner3 == 0) {
                    return setErrorSpinner(validateSpinner3);
                } else return true;

            default:
                return true;
        }
    }

    private boolean setErrorSpinner(Spinner spinner) {
        ((TextView) spinner.getSelectedView()).setError("Error message");
        return false;
    }

    private void editTextPrecio() {
        /*Esta funcion es para limitar el numero de decimales  del editText
         * */
        editTextPrecio = findViewById(R.id.editTextPrecio);

        editTextPrecio.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
                String str = editTextPrecio.getText().toString();
                if (str.isEmpty()) return;
                String str2 = PerfectDecimal(str, 6, 2);

                if (!str2.equals(str)) {
                    editTextPrecio.setText(str2);
                    int pos = editTextPrecio.getText().length();
                    editTextPrecio.setSelection(pos);
                }
            }
        });
    }

    public void btnPremio() {
        btnPremio = findViewById(R.id.btnPremio);
        btnPremio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportePremio = new Intent(ReporteCanje.this, ReportePremio.class);
                reportePremio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                reportePremio.putExtra("premiovoT", canjeVo.getPremioVo());

                startActivityForResult(reportePremio, REQUEST_CANJE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CANJE) {
            if (resultCode == RESULT_OK) {
                premioVoTest = (PremioVo) data.getSerializableExtra("premiovo");
                canjeVo.setPremioVo(premioVoTest);
            }
        }
    }

    public void spinner(int IdSpinner, List<String> lista) {
        Spinner newSpinner = findViewById(IdSpinner);
        ArrayAdapter<String> listaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newSpinner.setAdapter(listaAdapter);
    }

    public String PerfectDecimal(String str, int MAX_BEFORE_POINT, int MAX_DECIMAL) {
        if (str.charAt(0) == '.') str = "0" + str;
        int max = str.length();

        String rFinal = "";
        boolean after = false;
        int i = 0, up = 0, decimal = 0;
        char t;
        while (i < max) {
            t = str.charAt(i);
            if (t != '.' && !after) {
                up++;
                if (up > MAX_BEFORE_POINT) return rFinal;
            } else if (t == '.') {
                after = true;
            } else {
                decimal++;
                if (decimal > MAX_DECIMAL)
                    return rFinal;
            }
            rFinal = rFinal + t;
            i++;
        }
        return rFinal;
    }

}
