package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquiz.util.Prefs;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    private Button button_começarPartida;
    private Button button_ultimaPontuacao;
    private int score = 0;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        button_começarPartida = findViewById(R.id.button_iniciar_partida);
        button_ultimaPontuacao = findViewById(R.id.button_ultimo_score);

        button_começarPartida.setOnClickListener(this);
        button_ultimaPontuacao.setOnClickListener(this);

        prefs = new Prefs(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_iniciar_partida:
                if(verificarConexao(this)){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(this, "Conecte-se à internet para jogar!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ultimo_score:
                simpleAlert();
                break;
        }

    }

    @Override
    protected void onStart() {
        score = prefs.getHighScore();
        super.onStart();
    }

    public void simpleAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ult_pontuacao_dialogo);
        builder.setMessage(score + getString(R.string.pontos));
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.setCancelable(false);
        builder.show();
    }

    public static boolean verificarConexao(Activity activity){

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);

        if(cm != null){
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }else
            return false;
    }

}
