//02-06-21 / 10116077 / Yeremia Wahyu / akb-ul

package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCatatan extends AppCompatActivity {

    private EditText etText;
    private Memo memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcatatan);

        this.etText =  findViewById(R.id.etText);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel =  findViewById(R.id.btnCancel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            memo = (Memo)bundle.get("MEMO");
            if (memo != null){
                this.etText.setText(memo.getText());
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCatatan.this, MainCatatan.class);
                intent.putExtra("MEMO", memo);
                onSaveClicked();
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCatatan.this, MainCatatan.class);
                intent.putExtra("MEMO", memo);
                startActivity(intent);
            }
        });
    }

    public void onSaveClicked(){
        DatabaseAccess databaseAcces = DatabaseAccess.getInstance(this);
        databaseAcces.open();
        if (memo == null){
            Memo temp = new Memo();
            temp.setText(etText.getText().toString());
            databaseAcces.save(temp);
        }else {
            memo.setText(etText.getText().toString());
            databaseAcces.update(memo);
        }
        databaseAcces.close();
        this.finish();
    }
}