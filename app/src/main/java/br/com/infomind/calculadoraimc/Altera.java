package br.com.infomind.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by André Gomes on 02/08/2017.
 */

public class Altera extends AppCompatActivity {
    private static final int _PESO = 10;
    private static final int _ALTURA = 11;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar);

        Bundle params = getIntent().getExtras();
        this.tipo = params.getString("tipo");
        Double valor = params.getDouble("valor");

        Button btAlterar = (Button) findViewById(R.id.btnAlterar);

        if(this.tipo.equals("Peso:")){
            btAlterar.setText("Alterar Peso");
        }else{
            btAlterar.setText("Alterar Altura");
        }


        Button btRetornoar = (Button) findViewById(R.id.btnCancelar);

        TextView tp = (TextView) findViewById(R.id.txtTipo);
        tp.setText(this.tipo);

        EditText v = (EditText) findViewById(R.id.txtValor);
        v.setText(valor.toString());
    }

    public void alteraDados(View v){

        Intent intent= new Intent();

        Bundle data = new Bundle();
        EditText valor = (EditText) findViewById(R.id.txtValor);
        data.putDouble("valor", Double.parseDouble(valor.getText().toString()));

        intent.putExtras(data);

        setResult(RESULT_OK,intent);

        finish();
    }

    public void cancelarAlteracao(View v){
        setResult(RESULT_CANCELED, null);
        finish();
    }

}
