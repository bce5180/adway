package com.example.adway_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adway_project.Extensions.hideKeyboard
import com.example.adway_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        signUp()

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

    }

    private fun signUp() {
        binding.signUp.setOnClickListener {
            if (binding.etSignUpId.length() in 6..10 && binding.etSignUpPw.length() in 8..12
                && !binding.etSignUpNum.text.isNullOrBlank() && !binding.etSignUpName.text.isNullOrBlank()
            ) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", binding.etSignUpId.text.toString())
                    .putExtra("pw", binding.etSignUpPw.text.toString())
                    .putExtra("name", binding.etSignUpName.text.toString())
                    .putExtra("num", binding.etSignUpNum.text.toString())

                setResult(RESULT_OK, intent)
                Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()

                finish()

            } else
                Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
