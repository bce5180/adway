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
            override fun onItemClick(position: Int, item: RecommendationItem) {
                val detailFragment = when (position) {
                    0 -> DetailFragment.newInstance(R.drawable.image01, "강남역")
                    1 -> DetailFragment.newInstance(R.drawable.image10, "노원역")
                    2 -> DetailFragment.newInstance(R.drawable.image23, "마포역")
                    3 -> DetailFragment.newInstance(R.drawable.image02, "왕십리역")
                    4 -> DetailFragment.newInstance(R.drawable.image11, "혜화역")
                    5 -> DetailFragment.newInstance(R.drawable.image24, "행당역")
                    6 -> DetailFragment.newInstance(R.drawable.image03, "건대입구역")
                    7 -> DetailFragment.newInstance(R.drawable.image12, "명동역")
                    8 -> DetailFragment.newInstance(R.drawable.image25, "아차산역")
                    9 -> DetailFragment.newInstance(R.drawable.image04, "잠실역")
                    10 -> DetailFragment.newInstance(R.drawable.image13, "서울역")
                    11 -> DetailFragment.newInstance(R.drawable.image19, "여의도역")
                    12 -> DetailFragment.newInstance(R.drawable.image05, "사당역")
                    13 -> DetailFragment.newInstance(R.drawable.image14, "충무로역")
                    14 -> DetailFragment.newInstance(R.drawable.image20, "동대문역사문화공원역")
                    15 -> DetailFragment.newInstance(R.drawable.image06, "종합운동장역")
                    16 -> DetailFragment.newInstance(R.drawable.image15, "동대문역")
                    17 -> DetailFragment.newInstance(R.drawable.image21, "천호역")
                    18 -> DetailFragment.newInstance(R.drawable.image07, "신도림역")
                    19 -> DetailFragment.newInstance(R.drawable.image16, "미아사거리역")
                    20 -> DetailFragment.newInstance(R.drawable.image22, "종로3가역")
                    21 -> DetailFragment.newInstance(R.drawable.image09, "홍대입구역")
                    22 -> DetailFragment.newInstance(R.drawable.image18, "총신대입구역")
                    23 -> DetailFragment.newInstance(R.drawable.image08, "합정역")
                    24 -> DetailFragment.newInstance(R.drawable.image17, "성신여대입구역")
                    else -> DetailFragment.newInstance(R.drawable.image01, "강남역")

                }
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
        fun onItemClick(position: Int, item: RecommendationItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, listener)
    }


    class ViewHolder(itemView: View, val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: RecommendationItem, position: Int, listener: OnItemClickListener) {

            imageView.setImageResource(item.imageResId)
            textView.text = item.text

            itemView.setOnClickListener {
                listener.onItemClick(position, item)
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
