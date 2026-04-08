package ddwu.umc.chapter01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.umc.chapter01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.ivHappy.setOnClickListener {
            binding.tvHappy.setTextColor(getColor(R.color.happy_yellow))
        }

        binding.ivExcited.setOnClickListener {
            binding.tvExcited.setTextColor(getColor(R.color.excited_blue))
        }

        binding.ivNormal.setOnClickListener {
            binding.tvNormal.setTextColor(getColor(R.color.normal_purple))
        }

        binding.ivAnxious.setOnClickListener {
            binding.tvAnxious.setTextColor(getColor(R.color.anxious_green))
        }

        binding.ivAngry.setOnClickListener {
            binding.tvAngry.setTextColor(getColor(R.color.angry_red))
        }

    }
}