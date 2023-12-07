package com.example.adway_project

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.statusBarColor = Color.BLACK

        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, homeFragment) // fragment_container는 HomeFragment를 표시할 레이아웃의 ID
            addToBackStack(null) // 뒤로 가기 스택에 추가 (선택사항)
            commit()
        }

        val searchButton: Button = findViewById(R.id.buttonSearch)
        searchButton.setOnClickListener {
            val searchFragment = SearchFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, searchFragment) // fragment_container: SearchFragment를 표시할 레이아웃의 ID
                addToBackStack(null) // 뒤로 가기 스택에 추가 (선택사항)
                commit()
            }
        }

        val homeButton: Button = findViewById(R.id.buttonHome)
        homeButton.setOnClickListener {
            val homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, homeFragment) // fragment_container는 HomeFragment를 표시할 레이아웃의 ID
                addToBackStack(null) // 뒤로 가기 스택에 추가 (선택사항)
                commit()
            }
        }

        val designButton: Button = findViewById(R.id.buttonDesign)
        designButton.setOnClickListener {
            val designFragment = DesignFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, designFragment) // fragment_container는 DesignFragment를 표시할 레이아웃의 ID
                addToBackStack(null) // 뒤로 가기 스택에 추가 (선택사항)
                commit()
            }
        }

        val mypageButton: ImageButton = findViewById(R.id.buttonMypage)
        mypageButton.setOnClickListener {
            val mypageFragment = MyPageFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, mypageFragment) // fragment_container는 DesignFragment를 표시할 레이아웃의 ID
                addToBackStack(null) // 뒤로 가기 스택에 추가 (선택사항)
                commit()
            }
        }
    }
}