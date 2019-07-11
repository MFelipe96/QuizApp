package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_false;
    private Button button_true;
    private ImageButton nextAsk;
    private ImageButton previousAsk;
    private TextView myQuestion;


    private boolean respostaEcolhida;
    private int indexBank = 0;
    private int count = 7;

    private Question[] questionBank = new Question[]{

            new Question(R.string.question_day1, true),
            new Question(R.string.question_day2, true),
            new Question(R.string.question_day3, false),
            new Question(R.string.question_day4, true),
            new Question(R.string.question_day5, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question question = new Question(R.id.ask_my_test, true);

        button_false = findViewById(R.id.false_button);
        button_true = findViewById(R.id.true_button);
        myQuestion = findViewById(R.id.ask_my_test);
        nextAsk = findViewById(R.id.next_button);
        previousAsk = findViewById(R.id.previous_button);

        button_false.setOnClickListener(this);
        button_true.setOnClickListener(this);
        nextAsk.setOnClickListener(this);
        previousAsk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.false_button:
                checkResposta(false);
                break;

            case R.id.true_button:
                checkResposta(true);
                break;

            case R.id.next_button:
                indexBank = (indexBank + 1) % questionBank.length;
                myQuestion.setText(questionBank[indexBank].getAnswerResId());
                count++;
                break;

            case R.id.previous_button:
                //Todo: tratar o caso em que o usuário tenta voltar com indexBank = 0
                    indexBank = (indexBank + 1) % questionBank.length;
                    myQuestion.setText(questionBank[indexBank].getAnswerResId());
                    break;
        }


    }

    private void checkResposta(boolean respostaEscolhida){

        boolean respostaCerta = questionBank[indexBank].isAnswerTrue();
        int toastMensagemId;

        if(respostaEscolhida == respostaCerta)
            toastMensagemId = R.string.resposta_certa;
        else
            toastMensagemId = R.string.resposta_errada;

        Toast.makeText(getApplicationContext(),toastMensagemId, Toast.LENGTH_SHORT).show();
}
}
