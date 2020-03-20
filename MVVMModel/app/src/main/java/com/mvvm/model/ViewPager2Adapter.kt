package com.mvvm.model

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.model.databinding.ViewPager2AdapterBinding

/**
 * @ClassName: ViewPager2Adapter
 * @Description: 描述
 * @Author: xd on  2020-03-01 19:06
 */
class ViewPager2Adapter: RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder>(){
    private var list = emptyList<Int>()
    private lateinit var listener: () -> Unit
    private fun checkListener() = ::listener.isInitialized//::listener 调用函数类型
    fun setOnItemClickListener(e: ()->Unit){
        this.listener = e
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_view_pager_item, parent, false)
        return PagerViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(position,list)
        holder.binding.itemTitle.setOnCloseListener {
            holder.binding.itemTitle.visibility = View.GONE
        }
        holder.itemView.setOnClickListener{
            if (checkListener()) listener()
        }
    }

    fun setList(list1: List<Int>){
        this.list = list1
        notifyDataSetChanged()
    }

    class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: ViewPager2AdapterBinding = ViewPager2AdapterBinding.bind(itemView)
        private val colors = arrayOf("#CCFF99","#41F1E5","#8D41F1","#FF99CC")
        fun bindData(position: Int, list: List<Int>) {
            binding.viewPager2Item.text = list[position].toString()
            binding.viewPager2Item.setBackgroundColor(Color.parseColor(colors[position]))
        }
    }
}