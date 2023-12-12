package com.example.adway_project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adway_project.Extensions.hideKeyboard
import com.example.adway_project.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var activityResult: ActivityResultLauncher<Intent>
    lateinit var id: String
    lateinit var pw: String
    lateinit var name: String
    lateinit var num: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setResult()
        login()

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent)
        }

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }
    }


    private fun login() {
        binding.signIn.setOnClickListener {
            if (binding.etSignInId.text.toString() == id && binding.etSignInPw.text.toString() == pw) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("num", num)
                    putExtra("name", name)
                    putExtra("pw", pw)
                }

                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setResult() {
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                name = result.data?.getStringExtra("name") ?: ""
                num = result.data?.getStringExtra("num") ?: ""
            }
        }
    }
}
