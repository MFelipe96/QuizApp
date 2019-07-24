package com.example.appquiz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appquiz.R;
import com.example.appquiz.model.Pessoa;
import com.example.appquiz.util.Math;
import com.example.appquiz.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Math math;


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_NAME = "CREATE TABLE " + Util.TABLE_NAME + " (" + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                                    + Util.KEY_NAME + " TEXT," + Util.KEY_EMAIL + " TEXT," + Util.KEY_TELEFONE + " TEXT(9))";
        db.execSQL(CREATE_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE = String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);

    }

    public void addPessoa(Pessoa pessoa){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, pessoa.getNome());
        values.put(Util.KEY_EMAIL, pessoa.getEmail());
        values.put(Util.KEY_TELEFONE, pessoa.getTelefone());

        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    public Pessoa getPessoa(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_EMAIL, Util.KEY_TELEFONE},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)}, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(Integer.parseInt(cursor.getString(0)));
        pessoa.setNome(cursor.getString(1));
        pessoa.setEmail(cursor.getString(2));
        pessoa.setTelefone(cursor.getString(3));

        return pessoa;
    }

    public List<Pessoa> getAllPessoas(){

        math = new Math();
        List<Pessoa> pessoas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor.moveToFirst()){
            do{
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(cursor.getString(0)));
                pessoa.setNome(cursor.getString(1));
                pessoa.setEmail(cursor.getString(2));
                pessoa.setTelefone(cursor.getString(3));

                pessoas.add(pessoa);

            }while (cursor.moveToNext());
        }

        return pessoas;
    }


}
