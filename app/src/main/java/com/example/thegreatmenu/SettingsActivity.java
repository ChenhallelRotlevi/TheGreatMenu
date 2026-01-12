package com.example.thegreatmenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends BaseActivity { // יורש מ-BaseActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroupThemes);
        Button btnApply = findViewById(R.id.btnApplyTheme);

        // טעינת הבחירה הנוכחית
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String currentTheme = prefs.getString("SelectedTheme", "Orange");

        if (currentTheme.equals("Green")) ((RadioButton)findViewById(R.id.radioGreen)).setChecked(true);
        else if (currentTheme.equals("Night")) ((RadioButton)findViewById(R.id.radioNight)).setChecked(true);
        else ((RadioButton)findViewById(R.id.radioOrange)).setChecked(true);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTheme = "Orange";
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.radioGreen) selectedTheme = "Green";
                else if (selectedId == R.id.radioNight) selectedTheme = "Night";

                // שמירה ב-SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("SelectedTheme", selectedTheme);
                editor.apply();

                Toast.makeText(SettingsActivity.this, "העיצוב עודכן! מתחיל מחדש...", Toast.LENGTH_SHORT).show();

                // הפעלה מחדש של האפליקציה כדי שהשינוי יחול
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}