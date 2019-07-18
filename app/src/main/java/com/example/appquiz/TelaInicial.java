package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    private Button button_começarPartida;
    private Button button_ultimaPontuacao;
    private TextView textView_exibiScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        button_começarPartida = findViewById(R.id.button_iniciar_partida);
        button_ultimaPontuacao = findViewById(R.id.button_ultimo_score);
        textView_exibiScore = findViewById(R.id.textView_exibi_pts);

        button_começarPartida.setOnClickListener(this);
        button_ultimaPontuacao.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_iniciar_partida:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_ultimo_score:
                test();
                break;
        }

    }

    public void test(){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up);
        dialog.show();
    }


}
