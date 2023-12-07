package com.example.adway_project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class SearchFragment : Fragment(), RecyclerAdapter.OnItemClickListener {

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText

    private val originalList = listOf(
        Item(R.drawable.image05, "강남역"),
        Item(R.drawable.image06, "건대입구역"),
        Item(R.drawable.image10, "노원역"),
        Item(R.drawable.image15, "동대문역"),
        Item(R.drawable.image20, "동대문역사문화공원역"),
        Item(R.drawable.image23, "마포역"),
        Item(R.drawable.image16, "미아사거리역"),
        Item(R.drawable.image12, "명동역"),
        Item(R.drawable.image07, "사당역"),
        Item(R.drawable.image13, "서울역"),
        Item(R.drawable.image17, "성신여대입구역"),
        Item(R.drawable.image08, "신도림역"),
        Item(R.drawable.image25, "아차산역"),
        Item(R.drawable.image19, "여의도역"),
        Item(R.drawable.image09, "왕십리역"),
        Item(R.drawable.image01, "잠실역"),
        Item(R.drawable.image22, "종로3가역"),
        Item(R.drawable.image02, "종합운동장역"),
        Item(R.drawable.image18, "총신대입구역"),
        Item(R.drawable.image21, "천호역"),
        Item(R.drawable.image14, "충무로역"),
        Item(R.drawable.image24, "행당역"),
        Item(R.drawable.image03, "합정역"),
        Item(R.drawable.image11, "혜화역"),
        Item(R.drawable.image04, "홍대입구역")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 리사이클러뷰 초기화
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = RecyclerAdapter(originalList, this) // 초기 데이터
        recyclerView.adapter = recyclerAdapter

        // 검색 기능
        searchEditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // 필터링 메서드
    private fun filter(text: String) {
        val filteredList = originalList.filter {
            it.text.toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
        }
        recyclerAdapter.filterList(filteredList)
    }

    override fun onItemClick(item: Item) {
        val detailFragment = DetailFragment.newInstance(item.imageResId, item.text)

        // 프래그먼트 매니저를 사용하여 트랜잭션을 수행.
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, detailFragment) // fragment_container는 교체할 뷰의 ID입니다.
            addToBackStack(null) // Back stack에 추가하여 뒤로가기 버튼이 작동하도록 합니다.
            commit()
        }
    }
}