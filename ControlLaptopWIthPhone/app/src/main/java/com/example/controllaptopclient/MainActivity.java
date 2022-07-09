package com.example.controllaptopclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] list = new String[]{"Python керування", "Вийти"};
        ListView listView = findViewById(R.id.main_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,//simple_expandable_list_item_1
                list
        );
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case (0):
                    Intent intent = new Intent(
                            MainActivity.this, PyCommandsActivity.class);
                    EditText address = findViewById(R.id.ip);
                    intent.putExtra("ip", address.getText().toString());
                    startActivity(intent);
                    break;
                case (1):
                    MainActivity.this.finishAffinity();
                    break;
            }
        });
    }
}