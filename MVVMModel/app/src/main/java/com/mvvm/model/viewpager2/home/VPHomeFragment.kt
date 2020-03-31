package com.mvvm.model.viewpager2.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.model.App
import com.mvvm.model.R
import com.mvvm.model.databinding.FragmentHomeDataBinding
import com.mvvm.model.db.User


/**
 * VPHomeFragment.kt
 * Info: 首页
 * Created by cxd on 2020-03-31 15:54
 */

class VPHomeFragment : Fragment(), VPHomeContract.IView {
    private lateinit var binding: FragmentHomeDataBinding
    private var viewModel: VPHomeContract.IViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_vp, container, false)
        binding.apply {
            lifecycleOwner = this@VPHomeFragment
            view = this@VPHomeFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            VPHomeViewModel.Factory(arguments, App.application)
        ).get(VPHomeViewModel::class.java)
        setListener()
    }

    private fun setListener() {
        viewModel?.userData?.observe(this, Observer {
            it.forEach { user ->
                Log.i("cxd", "user->${user.id} ${user.firstName} ${user.lastName}")
            }
        })
    }

    override fun delete(view: View) {
        App.dataBase.userDao().deleteAll()
    }

    override fun add(view: View) {
        App.dataBase.userDao().insert(User(3, "上海", "都市"))
    }

    override fun findAll(view: View) {
        val list = App.dataBase.userDao().findAll()
        list.forEach { user ->
            Log.i("cxd", "user->${user.id} ${user.firstName} ${user.lastName}")
        }
    }

    companion object {
        fun getInstance(bundle: Bundle? = null): Fragment {
            val fragment = VPHomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}