package com.example.dts_vsga_aplikasidatabasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtNama;
    Button btSimpan, btGetAll;
    TextView tvHasil;
    DatabaseHelper databaseHelper;

    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = findViewById(R.id.txtNama);
        btSimpan = findViewById(R.id.btSimpan);
        btGetAll = findViewById(R.id.btGetAll);
        tvHasil = findViewById(R.id.tvHasil);

        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNama.getText().toString();
                long l = databaseHelper.AddStudent(name);
                if (l > 0) {
                    Toast.makeText(MainActivity.this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> all =  databaseHelper.getAll();
                if (all != null && !all.isEmpty()) {
                    StringBuffer buffer = new StringBuffer();
                    for (String name : all) {
                        buffer.append(name);
                        buffer.append(",");
                    }
                    String names = buffer.toString();
                    tvHasil.setText(names.substring(0, names.length()-1));
                }
            }
        });
    }
}