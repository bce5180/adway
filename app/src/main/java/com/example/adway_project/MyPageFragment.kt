package com.example.adway_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
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
        val name = requireActivity().intent.getStringExtra("name")
        val id = requireActivity().intent.getStringExtra("id")
        val num = requireActivity().intent.getStringExtra("num")

        with(binding) {
            tvName.text = name
            tvYourId.text = id
            tvYourNum.text = num
        }

        parentFragmentManager.setFragmentResultListener("resultStation", this) { requestKey, result ->
            val resultStation = result.getString("resultStation")
            Log.e("setFragmentResultLauncher", "$resultStation")

            binding.tvYourStaion.text = resultStation
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}