package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        /* apply data from mainActivity */
        this.findViewById<TextView>(R.id.userNameOutputID).apply {
            text = intent.getStringExtra("userName")
        }

        this.findViewById<TextView>(R.id.genderOutputID).apply {
            text = intent.getStringExtra("gender")
        }

        this.findViewById<TextView>(R.id.documentIDOutputID).apply {
            text = intent.getStringExtra("documentID")
        }

        this.findViewById<TextView>(R.id.dateOfBirthOutputID).apply {
            text = intent.getStringExtra("dateOfBirth")
        }

        /* button */
        val buttonSubmit = findViewById<Button>(R.id.buttonBack)
        buttonSubmit.setOnClickListener {
            onBackPressed()
        }
    }
}