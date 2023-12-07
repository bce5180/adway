package com.example.adway_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.net.Uri
import com.example.adway_project.databinding.FragmentDesignBinding


class DesignFragment : Fragment() {


    private lateinit var binding: FragmentDesignBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesignBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 이미지뷰 클릭 이벤트 처리
        binding.mustad.setOnClickListener {
            openWebView("https://www.must-ad.com/ad-design")
        }


        binding.gdia.setOnClickListener {
            openWebView("http://www.gidia.co.kr/advertisement/ad_landmark_v3.php")
        }


        binding.kmong.setOnClickListener {
            openWebView("https://kmong.com/category/1")
        }


        binding.popple.setOnClickListener {
            openWebView("http://www.ppcom.kr/design-view.php?idx=38")
        }


        binding.vitec.setOnClickListener {
            openWebView("http://vitec.co.kr/design/")
        }

        binding.abe9.setOnClickListener {
            openWebView("https://abe9.co.kr/content/poster/content")
        }
    }

    // 웹뷰 열기
    private fun openWebView(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
