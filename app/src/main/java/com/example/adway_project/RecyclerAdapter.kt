package com.example.adway_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private var items: List<Item>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image) // 이미지 뷰
        val textView: TextView = view.findViewById(R.id.item_text) // 텍스트 뷰

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(items[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.textView.text = currentItem.text
    }

    override fun getItemCount() = items.size

    // 검색을 위한 메서드
    fun filterList(filteredList: List<Item>) {
        items = filteredList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }
}