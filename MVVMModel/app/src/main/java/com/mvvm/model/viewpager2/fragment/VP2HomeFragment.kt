package com.mvvm.model.viewpager2.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mvvm.model.App
import com.mvvm.model.R
import com.mvvm.model.databinding.FragmentVp2Binding
import com.mvvm.model.db.User
import com.mvvm.model.viewpager2.VP2FragmentActivity

/**
 * @ClassName: VP2Fragment
 * @Description: 描述
 * @Author: xd on  2020-03-09 23:03
 */
class VP2HomeFragment : Fragment() {
    lateinit var binding: FragmentVp2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_vp2, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        when (args?.get(INDEX) ?: 0) {
            VP2FragmentActivity.PAGE_NEWS -> binding.listLinearLayout.setBackgroundColor(
                resources.getColor(
                    R.color.colorPrimary
                )
            )
            VP2FragmentActivity.PAGE_MINE -> binding.listLinearLayout.setBackgroundColor(
                resources.getColor(
                    R.color.colorPrimaryDark
                )
            )
            else ->
                binding.listLinearLayout.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    companion object {
        const val INDEX = "_index"
        fun getInstance(index: Int): VP2HomeFragment {
            val fragment = VP2HomeFragment()
            val bundle = Bundle()
            bundle.putInt(INDEX, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}