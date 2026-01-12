package com.example.thegreatmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class AddRecipeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // בעתיד: כאן נוסיף את הקוד לשמירה ב-Firebase Database
                Toast.makeText(AddRecipeActivity.this, "המתכון פורסם בהצלחה!", Toast.LENGTH_SHORT).show();
                finish(); // חזרה לפיד
            }
        });
    }
}