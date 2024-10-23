package com.example.maliciousapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // Sample data for the dropdown
    private String[] items = {"filehasher", "justask","serialintent"};
    private int selection ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the Spinner (dropdown)
        Spinner spinner = findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Always set the last element first, so i don't have to set it every time
        spinner.setSelection(items.length - 1);


        // Set the selection listener for the dropdown
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                selection = position;
                //String selectedItem = parent.getItemAtPosition(position).toString();
                // Display the selected item in a Toast message
                //Toast.makeText(MainActivity.this, "Selected: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing when nothing is selected
            }
        });

        ((Button) findViewById(R.id.btn_open)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch(selection){
                    case 0: intent = new Intent(MainActivity.this,filehasher.class);
                        break;
                    case 1: intent = new Intent(MainActivity.this,justask.class);
                        break;
                    case 2: intent = new Intent(MainActivity.this,serialintent.class);
                    default:
                        break;
                }
                startActivity(intent);
            }
        });

        /*Intent intent = new Intent(MainActivity.this,justask.class);
        startActivity(intent);
        Utils.showDialog(this,intent);*/


    }
}