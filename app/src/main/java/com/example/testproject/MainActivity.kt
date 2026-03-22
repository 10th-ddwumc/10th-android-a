package com.example.testproject // 혜민님의 패키지명으로 확인!

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. 뷰 바인딩 객체 선언 (XML 요소들을 편하게 부르기 위함)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. 기본 설정 (상태바/내비게이션바 영역 처리)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. 화면의 여백 설정 (기존 코드의 기능 유지)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. 우표와 텍스트를 짝지어 리스트로 만들기
        val stampPairs = listOf(
            Pair(binding.ivStampHappy, binding.tvStampHappyText),
            Pair(binding.ivStampExcited, binding.tvStampExcitedText),
            Pair(binding.ivStampNormal, binding.tvStampNormalText),
            Pair(binding.ivStampAnxious, binding.tvStampAnxiousText),
            Pair(binding.ivStampAngry, binding.tvStampAngryText)
        )

        // 5. 클릭 이벤트: 우표를 누르면 해당 텍스트 색깔 변경
        stampPairs.forEach { (imageView, textView) ->
            imageView.setOnClickListener {
                // 먼저 모든 텍스트를 검은색으로 초기화
                stampPairs.forEach { it.second.setTextColor(Color.BLACK) }

                // 클릭된 녀석만 파란색(혹은 원하는 색)으로 변경
                textView.setTextColor(Color.BLUE)
            }
        }
    }
}