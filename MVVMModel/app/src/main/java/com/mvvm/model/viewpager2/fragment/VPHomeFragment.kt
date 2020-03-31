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
import com.mvvm.model.databinding.FragmentHomeDataBinding
import com.mvvm.model.db.User


/**
 * VPHomeFragment.kt
 * Info: 首页
 * Created by cxd on 2020-03-31 15:54
 */

class VPHomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_vp, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = User(1, "南京", "古城")
        App.dataBase.userDao().createUser(user)
        val list = App.dataBase.userDao().findAll()
        Log.i("cxd", "user->${list[0].id} ${list[0].firstName} ${list[0].lastName}")

    }
    companion object {

        fun getInstance(bundle: Bundle? = null): Fragment {
            val fragment = VPHomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}