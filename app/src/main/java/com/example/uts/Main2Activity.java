//02-06-21 / 10116077 / Yeremia Wahyu / akb-ul

package com.example.uts;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            prefManager prefManager = new prefManager(getApplicationContext());
            prefManager.setFirstTimeLaunch(true);
            startActivity(new Intent(Main2Activity.this, Navigasidrawer.class));
            finish();
        }
    });

    }
}