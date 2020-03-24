package com.mvvm.model.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.model.R
import com.mvvm.model.databinding.ViewPager2AdapterBinding

/**
 * @ClassName: ViewPager2Adapter
 * @Description: ViewPager2
 * @Author: xd on  2020-03-01 19:06
 */
class ViewPager2Adapter : RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder>() {
    private var list = ArrayList<Int>()
    private lateinit var listener: () -> Unit
    private fun checkListener() = ::listener.isInitialized//::listener 调用函数类型
    fun setOnItemClickListener(e: ()->Unit){
        this.listener = e
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_view_pager_item, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(position, list)
        holder.binding.verticalBtn.setOnClickListener{
            if (checkListener()) listener()
        }
    }

    fun setList(arrayList: ArrayList<Int>) {
        list.clear()
        list = arrayList
        notifyDataSetChanged()
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ViewPager2AdapterBinding = ViewPager2AdapterBinding.bind(itemView)
        fun bindData(position: Int, list: List<Int>) {
            binding.itemTitle.text = "第${list[position]}个item"
        }
    }
}