package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appquiz.data.DatabaseHandler;
import com.example.appquiz.model.Pessoa;

import java.util.List;

public class Forms_cadastro extends AppCompatActivity {

    private EditText editText_nome;
    private EditText editText_email;
    private EditText editText_telefone;
    private Button button_cadastrar;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_cadastro);


        editText_nome = findViewById(R.id.editText_nome);
        editText_email = findViewById(R.id.editText_email);
        editText_telefone = findViewById(R.id.editText_telefone);
        button_cadastrar = findViewById(R.id.button_cadastro);

        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getBaseContext());
                String nomeString = editText_nome.getText().toString();
                String emailString = editText_email.getText().toString();
                String telefoneString = editText_telefone.getText().toString();

                pessoa = new Pessoa();
                pessoa.setNome(nomeString);
                pessoa.setEmail(emailString);
                pessoa.setTelefone(telefoneString);

                db.addPessoa(pessoa);

              //  Pessoa p = db.getPessoa(2);

                //Log.d("Teste", "onClick: " + pessoa.getNome());

                List<Pessoa> pessoaList = db.getAllPessoas();
                for(Pessoa pessoa: pessoaList){

                    Log.d("Forms", "onClick: " + pessoa.getId() + " / " + pessoa.getNome() + " / " + pessoa.getEmail() + " / " + pessoa.getTelefone());

                }

            }
        });
    }


}
