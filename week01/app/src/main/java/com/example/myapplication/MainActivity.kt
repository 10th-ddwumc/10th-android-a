package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivHappy = findViewById<ImageView>(R.id.iv_emotion_happy)
        val ivExcited = findViewById<ImageView>(R.id.iv_emotion_excited)
        val ivNeutral = findViewById<ImageView>(R.id.iv_emotion_neutral)
        val ivAnxious = findViewById<ImageView>(R.id.iv_emotion_anxious)
        val ivAngry = findViewById<ImageView>(R.id.iv_emotion_angry)

        val tvHappy = findViewById<TextView>(R.id.tv_happy)
        val tvExcited = findViewById<TextView>(R.id.tv_excited)
        val tvNeutral = findViewById<TextView>(R.id.tv_neutral)
        val tvAnxious = findViewById<TextView>(R.id.tv_anxious)
        val tvAngry = findViewById<TextView>(R.id.tv_angry)

        fun resetTextColor() {
            tvHappy.setTextColor(getColor(R.color.black))
            tvExcited.setTextColor(getColor(R.color.black))
            tvNeutral.setTextColor(getColor(R.color.black))
            tvAnxious.setTextColor(getColor(R.color.black))
            tvAngry.setTextColor(getColor(R.color.black))
        }

        ivHappy.setOnClickListener {
            resetTextColor()
            tvHappy.setTextColor(getColor(R.color.happy))
        }

        ivExcited.setOnClickListener {
            resetTextColor()
            tvExcited.setTextColor(getColor(R.color.excited))
        }

        ivNeutral.setOnClickListener {
            resetTextColor()
            tvNeutral.setTextColor(getColor(R.color.neutral))
        }

        ivAnxious.setOnClickListener {
            resetTextColor()
            tvAnxious.setTextColor(getColor(R.color.anxious))
        }

        ivAngry.setOnClickListener {
            resetTextColor()
            tvAngry.setTextColor(getColor(R.color.angry))
        }
    }
}