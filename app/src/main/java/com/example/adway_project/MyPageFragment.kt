package com.example.adway_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adway_project.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "바인딩에러" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireActivity().intent.getStringExtra("id")
        val name = requireActivity().intent.getStringExtra("mbti")
        val num = requireActivity().intent.getStringExtra("nickname")

        with(binding) {
            tvYourId.text = id
            tvName.text = name
            tvYourNum.text = num
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}