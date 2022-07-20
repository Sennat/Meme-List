package com.project.memelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.memelist.databinding.MemeListItemBinding
import com.project.memelist.model.MemeItem

class MemeAdapter(private val list: MutableList<MemeItem> = mutableListOf()) : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {
    fun setMemeList(newlist: List<MemeItem>) {
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

    inner class MemeViewHolder(private val binding: MemeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBinding(item: MemeItem) {
            binding.apply {
                tvListTop.text = item.topText
                tvListBottom.text = item.bottomText

                Glide.with(ivListMeme)
                    .load(item.image)
                    .into(ivListMeme)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(MemeListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
       holder.onBinding(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}