package com.example.thegreatmenu

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth

class FeedActivity : AppCompatActivity() {

    val allRecipes = arrayListOf(
        "פסטה ברוטב עגבניות (חלבי)",
        "חביתת ירק מושקעת (פרווה)",
        "עוף בתנור עם תפוחי אדמה (בשרי)",
        "סלט יווני עשיר (חלבי)",
        "טוסט נקניק (בשרי)",
        "שקשוקה חריפה (פרווה)"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val listView = findViewById<ListView>(R.id.listViewRecipes)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnAdd = findViewById<Button>(R.id.btnAddRecipe)
        val btnLogout = findViewById<Button>(R.id.btnLogout) // הכפתור החדש

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, allRecipes)
        listView.adapter = adapter

        etSearch.addTextChangedListener { text ->
            adapter.filter.filter(text)
        }

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddRecipeActivity::class.java))
        }

        // לוגיקת התנתקות
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut() // התנתקות מ-Firebase

            // חזרה למסך הראשי
            val intent = Intent(this, MainActivity::class.java)
            // ניקוי ההיסטוריה כדי שלא יוכלו לחזור אחורה עם כפתור החזרה
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}