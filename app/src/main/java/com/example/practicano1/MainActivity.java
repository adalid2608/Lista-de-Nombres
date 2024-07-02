package com.example.practicano1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;

    private ArrayList<String> dataOrigin;
    private ArrayAdapter<String> adapter;
    private ListView lvNames;

    private Button btnSave;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNames=findViewById(R.id.lv_names);
        txtNombre = findViewById(R.id.txt_nombre);

        dataOrigin=new ArrayList<>();

        adapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dataOrigin);
        lvNames.setAdapter(adapter);

        btnSave = findViewById(R.id.btn_guardar);
        btnOrder = findViewById(R.id.btn_ordenar);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNombre.getText().toString();
                dataOrigin.add(name);
                txtNombre.setText("");
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < dataOrigin.size() - 1; i++) {
                    for (int j = 0; j < dataOrigin.size() - i - 1; j++) {
                        if (dataOrigin.get(j).compareTo(dataOrigin.get(j + 1)) > 0) {
                            String temp = dataOrigin.get(j);
                            dataOrigin.set(j, dataOrigin.get(j + 1));
                            dataOrigin.set(j + 1, temp);
                        }
                    }
                }

                Toast.makeText(MainActivity.this, "¡Elementos ordenados por orden alfabético!", Toast.LENGTH_SHORT).show();
            }
        });

        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = dataOrigin.get(position);
                dataOrigin.removeIf(item::equals);
                Toast.makeText(MainActivity.this, "¡Se ha eliminado a " + item + " correctamente!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}