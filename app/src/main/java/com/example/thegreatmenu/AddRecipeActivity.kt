package com.example.thegreatmenu

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            // כאן תהיה הלוגיקה לשמירה ב-Firebase בעתיד
            Toast.makeText(this, "המתכון פורסם בהצלחה!", Toast.LENGTH_SHORT).show()
            finish() // חזרה לפיד
        }
    }
}