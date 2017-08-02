package br.com.infomind.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    private static final int _PESO = 10;
    private static final int _ALTURA = 11;
    private double peso;
    private double altura;
    private double imc;
    private String resultado;
    private Context c = this;
    private TextView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView p = (TextView) findViewById(R.id.txtPeso);
        TextView a = (TextView) findViewById(R.id.txtAltura);
        //Resultado
        this.r = (TextView) findViewById(R.id.txtResultado);

        this.peso = Double.parseDouble(p.getText().toString());
        this.altura = Double.parseDouble(a.getText().toString());

    }

    public void alterarDados(View v){

        Button b = (Button) v;

        Intent intent = new Intent(c, Altera.class);
        Bundle params = new Bundle();

        TextView valor;
        String tipo;
        int ID;

        if(b.getId()==R.id.btnPeso){
            valor = (TextView) findViewById(R.id.txtPeso);
            tipo="Peso:";
            ID=_PESO;
        }else{
            valor = (TextView) findViewById(R.id.txtAltura);
            tipo="Altura:";
            ID=_ALTURA;
        }

        params.putString("tipo", tipo);
        params.putDouble("valor", Double.parseDouble(valor.getText().toString()));

        intent.putExtras(params);

        startActivityForResult(intent, ID);

    }

    //Implementação do método Calcular
    public void calculaIMC(View v){

            if(this.peso==0 || this.altura==0){
                Toast.makeText(c, "Informe peso e altura.", Toast.LENGTH_SHORT).show();
                return;
            }

            this.imc  = this.peso/Math.pow(this.altura,2);

            if(this.imc<16){
                resultado="Magreza Grave";
            }else if(this.imc<17){
                resultado="Magreza Moderada";
            }else if(this.imc<18.5){
                resultado="Magreza Leve";
            }else if(this.imc<25){
                resultado="Saudável";
            }else if(this.imc<30){
                resultado="Sobrepeso";
            }else if(this.imc<35){
                resultado="Obesidade Grau I";
            }else if(this.imc<40){
                resultado="Obesidade Grau II Severa";
            }else if(this.imc>=40){
                resultado = "Obesidade Grau III Mórbida";
            }else{
                resultado = "---";
                return;
            }
            NumberFormat formato1 = NumberFormat.getNumberInstance();
            String s = (formato1.format(this.imc));
            this.r.setText("IMC: "+ s + " - " +resultado);

        //Toast.makeText(c, , Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            Bundle dados = data.getExtras();
            Double valor = dados.getDouble("valor");
            TextView tv;

            if (requestCode == _PESO) {
                //De qual opção veio o resultado?
                    tv = (TextView) findViewById(R.id.txtPeso);
                    tv.setText(valor.toString());
                    this.peso=Double.parseDouble(tv.getText().toString());
            } else {
                    tv = (TextView) findViewById(R.id.txtAltura);
                    tv.setText(valor.toString());
                    this.altura=Double.parseDouble(tv.getText().toString());
            }
        }
    }

}
