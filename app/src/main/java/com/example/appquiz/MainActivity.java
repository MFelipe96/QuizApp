package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquiz.data.AnswerListAsyncResponse;
import com.example.appquiz.data.QuestionBank;
import com.example.appquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_false;
    private Button button_true;
    private ImageButton nextAsk;
    private ImageButton previousAsk;
    private TextView textViewQuestion;
    private TextView textViewCounterQuestion;
    private  int currentQuestionIndex = 0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new QuestionBank().getQuestion(new AnswerListAsyncResponse() {
            @Override
            public void ProcessFinished(ArrayList<Question> questionArrayList) {
                textViewQuestion.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                textViewCounterQuestion.setText(currentQuestionIndex + " / " + questionList.size());
            }
        });

        button_false = findViewById(R.id.false_button);
        button_true = findViewById(R.id.true_button);
        textViewQuestion = findViewById(R.id.textView_questions);
        textViewCounterQuestion =findViewById(R.id.textView_counter);
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
                updateQuestion();
                break;

            case R.id.true_button:
                checkResposta(true);
                updateQuestion();
                break;

            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                updateCounterQuestion();
                break;

            case R.id.previous_button:
                if(currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                    updateQuestion();
                    updateCounterQuestion();
                }
                break;
        }

    }

    private void updateCounterQuestion() {
        textViewCounterQuestion.setText(currentQuestionIndex + " / " + questionList.size());
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        textViewQuestion.setText(question);
    }

    private void checkResposta(boolean respostaEscolhida){

        boolean respostaCerta = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMensagemId;

        if(respostaEscolhida == respostaCerta) {
            shakeAnimation(Color.GREEN);
            toastMensagemId = R.string.resposta_certa;
        }
        else {
            shakeAnimation(Color.RED);
            toastMensagemId = R.string.resposta_errada;
        }

        Toast.makeText(getApplicationContext(),toastMensagemId, Toast.LENGTH_SHORT).show();
    }

    private void shakeAnimation(final int cor) {

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        final CardView cardView = findViewById(R.id.ask_my_test);

        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(cor);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
