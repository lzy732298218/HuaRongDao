package com.example.android.huarongdao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void game1(View view) {
        button = findViewById(R.id.button1);
        String a = (String)button.getText();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("text", a);
        startActivity(intent);
    }

    public void game2(View view) {
        button = findViewById(R.id.button2);
        String a = (String)button.getText();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("text", a);
        startActivity(intent);
    }

    public void game3(View view) {
        button = findViewById(R.id.button3);
        String a = (String)button.getText();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("text", a);
        startActivity(intent);
    }
}
