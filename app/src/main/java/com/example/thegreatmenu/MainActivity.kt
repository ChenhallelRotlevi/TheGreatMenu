package com.example.thegreatmenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // אתחול Firebase Auth
        auth = FirebaseAuth.getInstance()

        // בדיקה אם המשתמש כבר מחובר - אם כן, דלג ישר לפיד
        if (auth.currentUser != null) {
            navigateToFeed()
        }

        val etEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val etPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // לוגיקת התחברות (Login)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            navigateToFeed()
                        } else {
                            Toast.makeText(baseContext, "התחברות נכשלה: ${task.exception?.message}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "נא להזין אימייל וסיסמה", Toast.LENGTH_SHORT).show()
            }
        }

        // לוגיקת הרשמה (Register) - ליצירת משתמש מהירה
        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(baseContext, "נרשמת בהצלחה!", Toast.LENGTH_SHORT).show()
                            navigateToFeed()
                        } else {
                            Toast.makeText(baseContext, "הרשמה נכשלה: ${task.exception?.message}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "נא להזין אימייל וסיסמה להרשמה", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToFeed() {
        val intent = Intent(this, FeedActivity::class.java)
        startActivity(intent)
        finish() // מונע חזרה למסך הלוגין בלחיצה על Back
    }
}