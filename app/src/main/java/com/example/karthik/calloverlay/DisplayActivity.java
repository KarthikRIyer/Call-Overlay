package com.example.karthik.calloverlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        this.setFinishOnTouchOutside(true);

        Bundle extras = getIntent().getExtras();

        String incomingNumber = "";
        String incomingName = "";
//        String emailID = "";
        String displayString = "";
        if(extras.containsKey("number")){
            incomingNumber = getIntent().getStringExtra("number");
            displayString = displayString+"INCOMING NUMBER : "+incomingNumber+"\n";
        }
        if(extras.containsKey("name")){
            incomingName = getIntent().getStringExtra("name");
            displayString = displayString+" NAME : "+incomingName+"\n";
        }
//        if(extras.containsKey("email")){
//            emailID = getIntent().getStringExtra("email");
//            displayString = displayString+" E-Mail ID : "+emailID+"\n";
//        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(displayString);

        Button okButton = findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
