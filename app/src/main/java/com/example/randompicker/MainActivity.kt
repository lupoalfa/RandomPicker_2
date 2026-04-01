package com.example.randompicker

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 현재 입력된 최대값을 저장
    private var currentMax: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showInputScreen()
    }

    // ── 입력 화면 표시 ──────────────────────────────────────────────
    private fun showInputScreen() {
        setContentView(R.layout.activity_main)

        val etNumber = findViewById<EditText>(R.id.etNumber)
        val btnPick  = findViewById<Button>(R.id.btnPick)

        btnPick.setOnClickListener {
            val text = etNumber.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "숫자를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val max = text.toIntOrNull()
            if (max == null || max < 1) {
                Toast.makeText(this, "1 이상의 숫자를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentMax = max
            showResultScreen(max)
        }
    }

    // ── 결과 화면 표시 ──────────────────────────────────────────────
    private fun showResultScreen(max: Int) {
        setContentView(R.layout.activity_result)

        val tvMax    = findViewById<TextView>(R.id.tvMax)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnAgain = findViewById<Button>(R.id.btnAgain)
        val btnBack  = findViewById<Button>(R.id.btnBack)

        tvMax.text = "1 ~ $max"
        pickAndDisplay(max, tvResult)

        // 한 번 더 추출
        btnAgain.setOnClickListener {
            pickAndDisplay(max, tvResult)
        }

        // 입력 화면으로 돌아가기
        btnBack.setOnClickListener {
            showInputScreen()
        }
    }

    // ── 랜덤 숫자 추출 & 애니메이션 ────────────────────────────────
    private fun pickAndDisplay(max: Int, tvResult: TextView) {
        val result = (1..max).random()
        tvResult.text = result.toString()

        // 팝인 애니메이션
        val anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        anim.duration = 300
        tvResult.startAnimation(anim)
    }
}
