package com.example.thegreatmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FeedActivity extends BaseActivity {

    private ArrayList<String> allRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        // נתונים לדוגמה
        allRecipes = new ArrayList<>();
        allRecipes.add("פסטה ברוטב עגבניות (חלבי)");
        allRecipes.add("חביתת ירק מושקעת (פרווה)");
        allRecipes.add("עוף בתנור עם תפוחי אדמה (בשרי)");
        allRecipes.add("סלט יווני עשיר (חלבי)");
        allRecipes.add("טוסט נקניק (בשרי)");
        allRecipes.add("שקשוקה חריפה (פרווה)");

        ListView listView = findViewById(R.id.listViewRecipes);
        Button btnAdd = findViewById(R.id.btnAddRecipe);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnSettings = findViewById(R.id.btnSettings);
        // שימוש באדפטר המותאם אישית שלנו (RecipeAdapter)
        RecipeAdapter adapter = new RecipeAdapter(this, allRecipes);
        listView.setAdapter(adapter);

        // מעבר להוספת מתכון
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        // התנתקות
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(FeedActivity.this, MainActivity.class);
                // ניקוי ההיסטוריה
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(FeedActivity.this, SettingsActivity.class));
        });


    }
}