package com.mvvm.model.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mvvm.model.R
import com.mvvm.model.databinding.FragmentVp2Binding

/**
 * @ClassName: VP2Fragment
 * @Description: 描述
 * @Author: xd on  2020-03-09 23:03
 */
class VP2Fragment : Fragment() {
    lateinit var binding: FragmentVp2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_vp2, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        when(args?.get(INDEX) ?: 0){
            ListActivity.PAGE_HOME -> binding.listLinearLayout.setBackgroundColor(resources.getColor(
                R.color.colorAccent
            ))
            ListActivity.PAGE_OTHERS -> binding.listLinearLayout.setBackgroundColor(resources.getColor(
                R.color.colorPrimary
            ))
            ListActivity.PAGE_ME -> binding.listLinearLayout.setBackgroundColor(resources.getColor(
                R.color.colorPrimaryDark
            ))
            else ->
                binding.listLinearLayout.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    companion object {
        const val INDEX = "_index"
        fun getInstance(index: Int): VP2Fragment {
            val fragment = VP2Fragment()
            val bundle = Bundle()
            bundle.putInt(INDEX,index)
            fragment.arguments = bundle
            return fragment
        }
    }
}