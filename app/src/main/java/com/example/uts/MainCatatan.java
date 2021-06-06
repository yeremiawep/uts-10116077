//02-06-21 / 10116077 / Yeremia Wahyu / akb-ul

package com.example.uts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainCatatan extends AppCompatActivity {

    private ListView listView;
    private DatabaseAccess databaseAccess;
    private List<Memo> memos;
    private Button btnBuat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincatatan);

        databaseAccess = DatabaseAccess.getInstance(this);

        listView = findViewById(R.id.listView);
        btnBuat =(Button) findViewById(R.id.btnBuat);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainCatatan.this, EditCatatan.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseAccess.open();
        this.memos = databaseAccess.getAllMemos();
        databaseAccess.close();
        MemoAdapter adapter = new MemoAdapter(this,memos);
        this.listView.setAdapter(adapter);
    }


    private class MemoAdapter extends ArrayAdapter<Memo>{

        MemoAdapter(@NonNull Context context, List<Memo> objects) {
            super(context, 0,  objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.layout_list_item, parent, false);
            }

            Button btnEdit = convertView.findViewById(R.id.btnEdit);
            Button btnDelete = convertView.findViewById(R.id.btnDelete);

            TextView txtDate =  convertView.findViewById(R.id.txtDate);
            TextView txtMemo = convertView.findViewById(R.id.txtMemo);

            final Memo memo = memos.get(position);
            txtDate.setText(memo.getDate());
            txtMemo.setText(memo.getShortText());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainCatatan.this, EditCatatan.class);
                    intent.putExtra("MEMO", memo);
                    startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAccess.open();
                    databaseAccess.delete(memo);
                    databaseAccess.close();

                    ArrayAdapter<Memo> adapter = (ArrayAdapter<Memo>) listView.getAdapter();
                    adapter.remove(memo);
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
