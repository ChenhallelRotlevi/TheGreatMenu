package com.example.thegreatmenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // טעינת ה-Theme לפני יצירת המסך
        loadTheme();
        super.onCreate(savedInstanceState);
    }

    private void loadTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String theme = sharedPreferences.getString("SelectedTheme", "Orange");

        switch (theme) {
            case "Green":
                setTheme(R.style.Theme_TheGreatMenu_Green);
                break;
            case "Night":
                setTheme(R.style.Theme_TheGreatMenu_Night);
                break;
            default:
                setTheme(R.style.Theme_TheGreatMenu);
                break;
        }
    }
}