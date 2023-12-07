package com.example.adway_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        // RecyclerView 초기화
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)


        // LinearLayoutManager를 사용하여 수직 스크롤을 허용하지 않도록 설정
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)


        // RecyclerView 어댑터 설정
        val adapter = RecommendationAdapter(object : RecommendationAdapter.OnItemClickListener {
            override fun onItemClick(item: RecommendationItem) {
                val detailFragment = DetailFragment.newInstance(item.imageResId, item.text)
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, detailFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        })
        recyclerView.adapter = adapter


        // 데이터 추가 (임시 데이터 예시)
        val data = mutableListOf<RecommendationItem>()


        // 역 이름 및 이미지 파일 이름 매핑
        val stationData = listOf(
            Pair("파노라마", R.drawable.station1),        // 강남
            Pair("와이드칼라", R.drawable.station2),      // 노원
            Pair("편성광고", R.drawable.station3),        // 마포
            Pair("와이드칼라", R.drawable.station4),      // 왕십리
            Pair("와이드칼라", R.drawable.station5),      // 혜화
            Pair("편성광고", R.drawable.station6),        // 행당
            Pair("와이드칼라", R.drawable.station7),      // 건대입구
            Pair("와이드칼라", R.drawable.station8),      // 명동
            Pair("포스터", R.drawable.station9),         // 아차산
            Pair("와이드칼라", R.drawable.station10),     // 잠실
            Pair("스크린도어", R.drawable.station11),     // 서울역
            Pair("와이드칼라", R.drawable.station12),     // 여의도
            Pair("스크린도어", R.drawable.station13),     // 사당
            Pair("스크린도어", R.drawable.station14),     // 충무로
            Pair("스크린도어", R.drawable.station15),     // 동대문역사문화공원
            Pair("벽면기둥래핑", R.drawable.station16),  // 종합운동장역
            Pair("와이드칼라", R.drawable.station17),     // 동대문
            Pair("와이드칼라", R.drawable.station18),     // 천호역
            Pair("스크린도어", R.drawable.station19),     // 신도림
            Pair("스크린도어", R.drawable.station20),     // 미아사거리
            Pair("스크린도어", R.drawable.station21),     // 종로3가
            Pair("디지털포스터", R.drawable.station22),   // 홍대입구
            Pair("스크린도어", R.drawable.station23),     // 총신대입구
            Pair("포스터", R.drawable.station24),        // 합정
            Pair("포스터", R.drawable.station25)         // 성신여대입구
        )

        // 데이터 추가
        for ((stationName, imageResId) in stationData) {
            data.add(RecommendationItem(stationName, imageResId))
        }
        adapter.submitList(data)
        return view


    }
}


data class RecommendationItem(val text: String, val imageResId: Int)

class RecommendationAdapter(private val listener: OnItemClickListener) : ListAdapter<RecommendationItem, RecommendationAdapter.ViewHolder>(RecommendationItemDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(item: RecommendationItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder(itemView: View, val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: RecommendationItem) {
            imageView.setImageResource(item.imageResId)
            textView.text = item.text

            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }
}


class RecommendationItemDiffCallback : DiffUtil.ItemCallback<RecommendationItem>() {
    override fun areItemsTheSame(oldItem: RecommendationItem, newItem: RecommendationItem): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: RecommendationItem, newItem: RecommendationItem): Boolean {
        return oldItem == newItem
    }
}
